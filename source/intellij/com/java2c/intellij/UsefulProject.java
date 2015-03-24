package com.java2c.intellij;

import com.intellij.analysis.AnalysisScope;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.InspectionProfile;
import com.intellij.codeInspection.ex.GlobalInspectionContextImpl;
import com.intellij.codeInspection.ex.InspectionManagerEx;
import com.intellij.compiler.CompilerConfiguration;
import com.intellij.openapi.compiler.*;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.TestDialog;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.profile.codeInspection.InspectionProjectProfileManager;
import com.intellij.psi.PsiManager;
import com.intellij.ui.content.ContentManager;
import com.java2c.intellij.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import com.java2c.intellij.rootPolicies.AbstractRootPolicy;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.intellij.codeInspection.ex.GlobalInspectionContextUtil.canRunInspections;
import static com.intellij.openapi.compiler.CompilerMessageCategory.ERROR;
import static com.intellij.openapi.compiler.CompilerMessageCategory.WARNING;
import static com.intellij.openapi.ui.Messages.setTestDialog;
import static com.java2c.intellij.NoTestDialog.AnswerAlwaysAsNo;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.util.Locale.ENGLISH;

public final class UsefulProject implements ProcessModuleOrder
{
	@NonNls
	@NotNull
	private static final String InvalidId = "invalid";

	@SuppressWarnings("PublicField")
	@NotNull
	public final Project project;

	@SuppressWarnings("PublicField")
	@NotNull
	public final ProjectRootManager projectRootManager;

	@SuppressWarnings("PublicField")
	@NotNull
	public final ArtifactManager artifactManager;

	@SuppressWarnings("PublicField")
	@NotNull
	public final ModuleManager moduleManager;

	@SuppressWarnings("PublicField")
	@NotNull
	public final PsiManager psiManager;

	@NotNull
	public final PathMacroManager pathMacroManager;

	@SuppressWarnings("PublicField")
	@NotNull
	public final CompilerManager compilerManager;

	@SuppressWarnings("PublicField")
	@NotNull
	public final CompilerConfiguration compilerConfiguration;

	@SuppressWarnings("PublicField")
	@NotNull
	public final InspectionManagerEx inspectionManagerEx;

	@SuppressWarnings("PublicField")
	@NotNull
	public final InspectionProjectProfileManager inspectionProjectProfileManager;

	@SuppressWarnings("PublicField")
	@NotNull
	public final InspectionProfile inspectionProfile;

	@SuppressWarnings("PublicField")
	@NotNull
	public final AnalysisScope analysisScope;

	// project may be ProjectEx
	public UsefulProject(@NotNull final Project project)
	{
		this.project = project;
		projectRootManager = projectRootManager();
		artifactManager = artifactManager();
		moduleManager = moduleManager();
		psiManager = psiManager();
		pathMacroManager = pathMacroManager();
		compilerManager = compilerManager();
		compilerConfiguration = compilerConfiguration();
		inspectionManagerEx = inspectionManagerEx();
		inspectionProjectProfileManager = inspectionProjectProfileManager();

		inspectionProfile = inspectionProjectProfileOrRootProfile();

		// This is done by IntelliJ's InspectionApplication logic
		inspectionManagerEx.setProfile(inspectionProfile.getName());

		analysisScope = new AnalysisScope(project);
	}

	public boolean validateArtifacts(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		final List<? extends Artifact> allArtifactsIncludingInvalid = artifactManager.getAllArtifactsIncludingInvalid();
		assert allArtifactsIncludingInvalid != null;
		boolean allArtifactsValid = true;
		for (final Artifact artifact : allArtifactsIncludingInvalid)
		{
			final ArtifactType artifactType = artifact.getArtifactType();
			if (InvalidId.equals(artifactType.getId()))
			{
				final String format = format(ENGLISH, "The artifact '%1$s' is invalid", artifact.getName()); //NON-NLS
				assert format != null;

				assert ERROR != null;
				projectValidationMessagesRecorder.record(project, ERROR, format);
				allArtifactsValid = false;
			}
		}
		return allArtifactsValid;
	}

	@NotNull
	public List<ProcessModuleOrder> usefulModulesSortedInDependencyOrder()
	{
		final Module[] sortedModules = moduleManager.getSortedModules();
		final List<ProcessModuleOrder> usefulModulesSorted = new ArrayList<ProcessModuleOrder>(sortedModules.length);
		for (final Module sortedModule : sortedModules)
		{
			usefulModulesSorted.add(new UsefulModule(sortedModule));
		}
		return usefulModulesSorted;
	}

	@Override
	public void validateModuleOrderEntriesInModuleDependencyOrder(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		for (final ProcessModuleOrder processModuleOrder : usefulModulesSortedInDependencyOrder())
		{
			processModuleOrder.validateModuleOrderEntriesInModuleDependencyOrder(projectValidationMessagesRecorder);
		}
	}

	@Override
	public void useModuleOrderEntriesInModuleDependencyOrder(@NotNull final AbstractRootPolicy<Object> abstractRootPolicy)
	{
		for (final ProcessModuleOrder processModuleOrder : usefulModulesSortedInDependencyOrder())
		{
			processModuleOrder.useModuleOrderEntriesInModuleDependencyOrder(abstractRootPolicy);
		}
	}

	public boolean validateCanRunInspections(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		if (canRunInspections(project, false))
		{
			return true;
		}
		assert WARNING != null;
		projectValidationMessagesRecorder.record(project, WARNING, "We can not run inspections");
		return false;
	}

	public void validateInspections(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		inspect(new ValidatingProblemRefElementHandler(projectValidationMessagesRecorder));
	}

	public void rebuild(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		final TestDialog oldValue = setTestDialog(AnswerAlwaysAsNo);
		compilerManager.rebuild(new RebuildCompileStatusNotification(projectValidationMessagesRecorder, project)
		{
			@Override
			public void finished(final boolean aborted, final int errors, final int warnings, @NotNull final CompileContext compileContext)
			{
				super.finished(aborted, errors, warnings, compileContext);
				setTestDialog(oldValue);
			}
		});
	}

	public void inspect(@NotNull final ProblemRefElementHandler problemRefElementHandler)
	{
		final NotNullLazyValue<ContentManager> contentManager = inspectionManagerEx.getContentManager();
		assert contentManager != null;

		final Set<GlobalInspectionContextImpl> runningContexts = inspectionManagerEx.getRunningContexts();
		assert runningContexts != null;

		final UsefulGlobalInspectionContextImpl usefulGlobalInspectionContext = new UsefulGlobalInspectionContextImpl(project, contentManager, runningContexts, inspectionProfile, problemRefElementHandler);
		@SuppressWarnings("AccessOfSystemProperties") final boolean runGlobalToolsOnly = getProperty("idea.no.local.inspections") != null;
		usefulGlobalInspectionContext.inspect(analysisScope, runGlobalToolsOnly);
	}

	@NotNull
	private ProjectRootManager projectRootManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final ProjectRootManager projectRootManager = ProjectRootManager.getInstance(project);
		assert projectRootManager != null;
		return projectRootManager;
	}

	@NotNull
	private ArtifactManager artifactManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final ArtifactManager artifactManager = ArtifactManager.getInstance(project);
		assert artifactManager != null;
		return artifactManager;
	}

	@NotNull
	private ModuleManager moduleManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final ModuleManager moduleManager = ModuleManager.getInstance(project);
		assert moduleManager != null;
		return moduleManager;
	}

	@NotNull
	private PsiManager psiManager()
	{
		return PsiManager.getInstance(project);
	}

	@NotNull
	private PathMacroManager pathMacroManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final PathMacroManager pathMacroManager = PathMacroManager.getInstance(project);
		assert pathMacroManager != null;
		return pathMacroManager;
	}

	@NotNull
	private CompilerManager compilerManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final CompilerManager compilerManager = CompilerManager.getInstance(project);
		assert compilerManager != null;
		return compilerManager;
	}

	@NotNull
	private CompilerConfiguration compilerConfiguration()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final CompilerConfiguration compilerConfiguration = CompilerConfiguration.getInstance(project);
		assert compilerConfiguration != null;
		return compilerConfiguration;
	}

	@NotNull
	private InspectionManagerEx inspectionManagerEx()
	{
		final InspectionManager inspectionManager = InspectionManager.getInstance(project);
		assert inspectionManager != null;
		return (InspectionManagerEx) inspectionManager;
	}

	@NotNull
	private InspectionProjectProfileManager inspectionProjectProfileManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final InspectionProjectProfileManager inspectionProjectProfileManager = InspectionProjectProfileManager.getInstance(project);
		assert inspectionProjectProfileManager != null;
		return inspectionProjectProfileManager;
	}

	@NotNull
	private InspectionProfile inspectionProjectProfileOrRootProfile()
	{
		return inspectionProjectProfileManager.getInspectionProfile();
	}
}

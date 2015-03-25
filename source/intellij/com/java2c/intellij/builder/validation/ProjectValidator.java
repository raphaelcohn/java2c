package com.java2c.intellij.builder.validation;

import com.intellij.openapi.project.Project;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.artifacts.ArtifactType;
import com.java2c.intellij.builder.validation.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import com.java2c.intellij.useful.UsefulProject;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.codeInspection.ex.GlobalInspectionContextUtil.canRunInspections;
import static com.intellij.openapi.compiler.CompilerMessageCategory.ERROR;
import static com.intellij.openapi.compiler.CompilerMessageCategory.WARNING;
import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class ProjectValidator
{
	@NonNls
	@NotNull
	private static final String InvalidId = "invalid";

	@NotNull
	private final UsefulProject usefulProject;

	@NotNull
	private final Project project;

	@NotNull
	private final ArtifactManager artifactManager;

	public ProjectValidator(@NotNull final UsefulProject usefulProject)
	{
		this.usefulProject = usefulProject;
		project = usefulProject.project;
		artifactManager = usefulProject.artifactManager;
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

	public void validateModuleOrderEntriesInModuleDependencyOrder(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		usefulProject.useModuleOrderEntriesInModuleDependencyOrder(OrderEntryValidatingRootPolicy.OrderEntryValidatingRootPolicyInstance, projectValidationMessagesRecorder);
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
		usefulProject.inspect(new ValidatingProblemRefElementHandler(projectValidationMessagesRecorder));
	}
}

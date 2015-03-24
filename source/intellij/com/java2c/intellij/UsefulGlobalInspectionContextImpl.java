package com.java2c.intellij;

import com.intellij.analysis.AnalysisScope;
import com.intellij.codeHighlighting.HighlightDisplayLevel;
import com.intellij.codeInspection.InspectionProfile;
import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.codeInspection.ex.GlobalInspectionContextImpl;
import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.codeInspection.ex.Tools;
import com.intellij.codeInspection.ui.InspectionToolPresentation;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.ui.content.ContentManager;
import com.java2c.intellij.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UsefulGlobalInspectionContextImpl extends GlobalInspectionContextImpl
{
	@NotNull
	private final Map<InspectionToolWrapper<?, ?>, AbstractInspectionToolPresentation> presentations;

	@NotNull
	private final ProblemRefElementHandler problemRefElementHandler;

	// Replicates logic of InspectionManagerEx.createNewGlobalContext
	@SuppressWarnings("ThisEscapedInObjectConstruction")
	public UsefulGlobalInspectionContextImpl(@NotNull final Project project, @NotNull final NotNullLazyValue<ContentManager> contentManager, @NotNull final Collection<GlobalInspectionContextImpl> runningContexts, @NotNull final InspectionProfile inspectionProfile, @NotNull final ProblemRefElementHandler problemRefElementHandler)
	{
		super(project, contentManager);
		this.problemRefElementHandler = problemRefElementHandler;
		runningContexts.add(this);
		setExternalProfile(inspectionProfile);
		presentations = new LinkedHashMap<InspectionToolWrapper<?, ?>, AbstractInspectionToolPresentation>(16);
	}

	@Override
	@NotNull
	public InspectionProfile getCurrentProfile()
	{
		final InspectionProfile currentProfile = super.getCurrentProfile();
		assert currentProfile != null;
		return currentProfile;
	}

	@Override
	@NotNull
	public Map<String, Tools> getTools()
	{
		final Map<String, Tools> tools = super.getTools();
		assert tools != null;
		return tools;
	}

	@SuppressWarnings("RefusedBequest")
	@NotNull
	@Override
	public InspectionToolPresentation getPresentation(@SuppressWarnings("rawtypes") @NotNull final InspectionToolWrapper toolWrapper)
	{
		@Nullable final InspectionToolPresentation inspectionToolPresentation = presentations.get(toolWrapper);
		if (inspectionToolPresentation != null)
		{
			return inspectionToolPresentation;
		}

		final UsefulInspectionToolPresentation usefulInspectionToolPresentation = new UsefulInspectionToolPresentation(this, toolWrapper, problemRefElementHandler);
		presentations.put(toolWrapper, usefulInspectionToolPresentation);
		return usefulInspectionToolPresentation;
	}

	public final void inspect(@NotNull final AnalysisScope scope, final boolean runGlobalToolsOnly)
	{
		performInspectionsWithProgress(scope, runGlobalToolsOnly);
	}

	@NotNull
	private static <K> K nonNullKey(@NotNull final Entry<K, ?> entry)
	{
		final K key = entry.getKey();
		assert key != null;
		return key;
	}

}

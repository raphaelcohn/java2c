package com.java2c.intellij.inspection.inspectionToolPresentationFactories;

import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.codeInspection.ui.InspectionToolPresentation;
import com.java2c.intellij.inspection.ProblemRefElementHandler;
import com.java2c.intellij.inspection.UsefulGlobalInspectionContextImpl;
import com.java2c.intellij.inspection.inspectionToolPresentations.UsefulInspectionToolPresentation;
import org.jetbrains.annotations.NotNull;

public final class UsefulInspectionToolPresentationFactory implements InspectionToolPresentationFactory
{
	@NotNull
	private final ProblemRefElementHandler problemRefElementHandler;

	public UsefulInspectionToolPresentationFactory(@NotNull final ProblemRefElementHandler problemRefElementHandler)
	{
		this.problemRefElementHandler = problemRefElementHandler;
	}

	@NotNull
	@Override
	public InspectionToolPresentation inspectionToolPresentation(@NotNull final UsefulGlobalInspectionContextImpl usefulGlobalInspectionContext, @NotNull final InspectionToolWrapper<?, ?> inspectionToolWrapper)
	{
		return new UsefulInspectionToolPresentation(usefulGlobalInspectionContext, inspectionToolWrapper, problemRefElementHandler);
	}
}

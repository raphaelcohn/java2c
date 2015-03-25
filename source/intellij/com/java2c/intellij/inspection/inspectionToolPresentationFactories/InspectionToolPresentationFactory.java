package com.java2c.intellij.inspection.inspectionToolPresentationFactories;

import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.codeInspection.ui.InspectionToolPresentation;
import com.java2c.intellij.inspection.UsefulGlobalInspectionContextImpl;
import org.jetbrains.annotations.NotNull;

public interface InspectionToolPresentationFactory
{
	@NotNull
	InspectionToolPresentation inspectionToolPresentation(@NotNull final UsefulGlobalInspectionContextImpl usefulGlobalInspectionContext, @NotNull final InspectionToolWrapper<?, ?> inspectionToolWrapper);
}

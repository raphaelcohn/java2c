package com.java2c.intellij.inspection;

import com.intellij.codeInspection.reference.RefElement;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ProblemRefElementHandler
{
	void handleProblemRefElement(@NotNull final RefElement refElement, final boolean filterSuppressed, @Nullable final PsiElement psiElement, @NotNull final HighlightSeverity severity, @NotNull final String descriptionMessage);
}

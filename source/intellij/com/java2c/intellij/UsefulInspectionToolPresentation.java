package com.java2c.intellij;

import com.intellij.codeHighlighting.HighlightDisplayLevel;
import com.intellij.codeInsight.daemon.HighlightDisplayKey;
import com.intellij.codeInspection.CommonProblemDescriptor;
import com.intellij.codeInspection.InspectionProfile;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.codeInspection.ex.ScopeToolState;
import com.intellij.codeInspection.ex.Tools;
import com.intellij.codeInspection.reference.RefElement;
import com.intellij.codeInspection.reference.RefEntity;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPsiElementPointer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.codeInsight.daemon.HighlightDisplayKey.find;
import static com.intellij.codeInspection.ProblemDescriptorUtil.renderDescriptionMessage;
import static com.intellij.lang.annotation.HighlightSeverity.GENERIC_SERVER_ERROR_OR_WARNING;

public final class UsefulInspectionToolPresentation extends AbstractInspectionToolPresentation
{
	private static final boolean AppendLineNumber = true;

	@NotNull
	private final InspectionToolWrapper<?, ?> inspectionToolWrapper;

	@NotNull
	private final ProblemRefElementHandler problemRefElementHandler;

	public UsefulInspectionToolPresentation(@NotNull final UsefulGlobalInspectionContextImpl globalInspectionContext, @NotNull final InspectionToolWrapper<?, ?> inspectionToolWrapper, @NotNull final ProblemRefElementHandler problemRefElementHandler)
	{
		super(globalInspectionContext);
		this.inspectionToolWrapper = inspectionToolWrapper;
		this.problemRefElementHandler = problemRefElementHandler;
	}

	@Override
	public void addProblemElement(@NotNull final RefEntity refElement, final boolean filterSuppressed, @NotNull final CommonProblemDescriptor... descriptions)
	{
		if (!refElement.isValid())
		{
			return;
		}

		final RefElement actualRefElement = (RefElement) refElement;

		for (final CommonProblemDescriptor commonProblemDescriptor : descriptions)
		{
			@Nullable final PsiElement psiElement;

			if (commonProblemDescriptor instanceof ProblemDescriptor)
			{
				final ProblemDescriptor problemDescriptor = (ProblemDescriptor) commonProblemDescriptor;
				psiElement = problemDescriptor.getPsiElement();
			}
			else
			{
				psiElement = actualRefElement.getElement();
			}

			final HighlightSeverity severity = severity(actualRefElement);
			final String descriptionMessage = renderDescriptionMessage(commonProblemDescriptor, psiElement, AppendLineNumber);

			problemRefElementHandler.handleProblemRefElement(actualRefElement, filterSuppressed, psiElement, severity, descriptionMessage);
		}
	}

	// derived from DefaultInspectionToolPresentation.getSeverity
	@NotNull
	private HighlightSeverity severity(@NotNull final RefElement refElement)
	{
		final SmartPsiElementPointer<?> pointer = refElement.getPointer();
		assert pointer != null;

		@Nullable final PsiElement psiElement = pointer.getContainingFile();
		if (psiElement == null)
		{
			assert GENERIC_SERVER_ERROR_OR_WARNING != null;
			return GENERIC_SERVER_ERROR_OR_WARNING;
		}

		final String severityDelegateName = inspectionToolWrapper.getShortName();
		final Tools tools = globalInspectionContext.getTools().get(severityDelegateName);
		if (tools != null)
		{
			for (final ScopeToolState state : tools.getTools())
			{
				final InspectionToolWrapper<?, ?> toolWrapper = state.getTool();
				//noinspection ObjectEquality
				if (toolWrapper == inspectionToolWrapper)
				{
					return getSeverity(psiElement, severityDelegateName);
				}
			}
		}

		return getSeverity(psiElement, severityDelegateName);
	}

	@NotNull
	private HighlightSeverity getSeverity(@Nullable final PsiElement psiElement, @NotNull final String severityDelegateName)
	{
		final InspectionProfile currentProfile = globalInspectionContext.getCurrentProfile();
		@Nullable final HighlightDisplayKey inspectionToolKey = find(severityDelegateName);
		if (inspectionToolKey == null)
		{
			assert GENERIC_SERVER_ERROR_OR_WARNING != null;
			return GENERIC_SERVER_ERROR_OR_WARNING;
		}
		final HighlightDisplayLevel errorLevel = currentProfile.getErrorLevel(inspectionToolKey, psiElement);
		assert errorLevel != null;
		return errorLevel.getSeverity();
	}

}

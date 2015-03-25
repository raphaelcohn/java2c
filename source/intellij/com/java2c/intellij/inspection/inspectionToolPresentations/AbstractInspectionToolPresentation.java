package com.java2c.intellij.inspection.inspectionToolPresentations;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.CommonProblemDescriptor;
import com.intellij.codeInspection.QuickFix;
import com.intellij.codeInspection.ex.GlobalInspectionContextImpl;
import com.intellij.codeInspection.ex.HTMLComposerImpl;
import com.intellij.codeInspection.ex.InspectionRVContentProvider;
import com.intellij.codeInspection.ex.QuickFixAction;
import com.intellij.codeInspection.reference.RefEntity;
import com.intellij.codeInspection.reference.RefModule;
import com.intellij.codeInspection.ui.InspectionNode;
import com.intellij.codeInspection.ui.InspectionToolPresentation;
import com.intellij.codeInspection.ui.InspectionTreeNode;
import com.intellij.openapi.vcs.FileStatus;
import com.java2c.intellij.inspection.UsefulGlobalInspectionContextImpl;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyList;

@SuppressWarnings({"ClassWithTooManyMethods", "AbstractClassWithOnlyOneDirectInheritor"})
public abstract class AbstractInspectionToolPresentation implements InspectionToolPresentation
{
	@NotNull
	protected final UsefulGlobalInspectionContextImpl globalInspectionContext;

	protected AbstractInspectionToolPresentation(@NotNull final UsefulGlobalInspectionContextImpl globalInspectionContext)
	{
		this.globalInspectionContext = globalInspectionContext;
	}

	@Override
	public final void addProblemElement(@Nullable final RefEntity refEntity, @NotNull final CommonProblemDescriptor... commonProblemDescriptors)
	{
		if (refEntity == null)
		{
			return;
		}
		addProblemElement(refEntity, true, commonProblemDescriptors);
	}

	@NotNull
	@Override
	public final InspectionNode createToolNode(@SuppressWarnings("ParameterHidesMemberVariable") @NotNull final GlobalInspectionContextImpl globalInspectionContext, @NotNull final InspectionNode node, @NotNull final InspectionRVContentProvider provider, @NotNull final InspectionTreeNode parentNode, final boolean showStructure)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void updateContent()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean hasReportedProblems()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	@NotNull
	public final Map<String, Set<RefEntity>> getContent()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	@NotNull
	public final Map<String, Set<RefEntity>> getOldContent()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void ignoreCurrentElement(@NotNull final RefEntity refEntity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void amnesty(@NotNull final RefEntity refEntity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void cleanup()
	{
	}

	@Override
	public final void finalCleanup()
	{
	}

	@Override
	public final boolean isGraphNeeded()
	{
		return true;
	}

	@Override
	public final boolean isElementIgnored(final RefEntity element)
	{
		return false;
	}

	@NotNull
	@Override
	public final FileStatus getElementStatus(@NotNull final RefEntity element)
	{
		throw new UnsupportedOperationException();
	}

	@NotNull
	@Override
	public final Collection<RefEntity> getIgnoredRefElements()
	{
		return emptyList();
	}

	@Nullable
	@Override
	public final IntentionAction findQuickFixes(@NotNull final CommonProblemDescriptor descriptor, final String hint)
	{
		return null;
	}

	@NotNull
	@Override
	public final HTMLComposerImpl getComposer()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void exportResults(@NotNull final Element parentNode, @NotNull final RefEntity refEntity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	@NotNull
	public final Set<RefModule> getModuleProblems()
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@Override
	public final QuickFixAction[] getQuickFixes(@NotNull final RefEntity[] refElements)
	{
		throw new UnsupportedOperationException();
	}

	@NotNull
	@Override
	public final Map<RefEntity, CommonProblemDescriptor[]> getProblemElements()
	{
		throw new UnsupportedOperationException();
	}

	@NotNull
	@Override
	public final Collection<CommonProblemDescriptor> getProblemDescriptors()
	{
		throw new UnsupportedOperationException();
	}

	@NotNull
	@Override
	public final FileStatus getProblemStatus(@NotNull final CommonProblemDescriptor descriptor)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean isOldProblemsIncluded()
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@Override
	public final Map<RefEntity, CommonProblemDescriptor[]> getOldProblemElements()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean isProblemResolved(final RefEntity refEntity, final CommonProblemDescriptor descriptor)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void ignoreCurrentElementProblem(@NotNull final RefEntity refEntity, @NotNull final CommonProblemDescriptor descriptor)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void ignoreProblem(@NotNull final CommonProblemDescriptor descriptor, @SuppressWarnings("rawtypes") @NotNull final QuickFix fix)
	{
		throw new UnsupportedOperationException();
	}

	@NotNull
	@Override
	public final GlobalInspectionContextImpl getContext()
	{
		return globalInspectionContext;
	}

	@Override
	public final void ignoreProblem(final RefEntity refEntity, final CommonProblemDescriptor problem, final int idx)
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@Override
	public final QuickFixAction[] extractActiveFixes(@NotNull final RefEntity[] refElements, @NotNull final Map<RefEntity, Set<QuickFix>> actions)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void exportResults(@NotNull final Element parentNode)
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@Override
	public final CommonProblemDescriptor[] getDescriptions(@NotNull final RefEntity refEntity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final void ignoreElement(@NotNull final RefEntity refEntity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public final RefEntity getElement(@NotNull final CommonProblemDescriptor descriptor)
	{
		throw new UnsupportedOperationException();
	}
}

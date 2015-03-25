package com.java2c.intellij.builder.validation;

import com.intellij.codeInspection.reference.RefElement;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPsiElementPointer;
import com.java2c.intellij.inspection.ProblemRefElementHandler;
import com.java2c.intellij.builder.validation.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.openapi.compiler.CompilerMessageCategory.ERROR;
import static com.intellij.openapi.compiler.CompilerMessageCategory.INFORMATION;
import static com.intellij.openapi.compiler.CompilerMessageCategory.WARNING;
import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class ValidatingProblemRefElementHandler implements ProblemRefElementHandler
{
	@NotNull
	private final ProjectValidationMessagesRecorder projectValidationMessagesRecorder;

	public ValidatingProblemRefElementHandler(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		this.projectValidationMessagesRecorder = projectValidationMessagesRecorder;
	}

	@SuppressWarnings("IfMayBeConditional")
	@Override
	public void handleProblemRefElement(@NotNull final RefElement refElement, final boolean filterSuppressed, @Nullable final PsiElement psiElement, @NotNull final HighlightSeverity severity, @NotNull final String descriptionMessage)
	{
		final CompilerMessageCategory compilerMessageCategory;
		if (severity.compareTo(HighlightSeverity.WEAK_WARNING) <= 0)
		{
			compilerMessageCategory = INFORMATION;
		}
		else if (severity.compareTo(HighlightSeverity.WARNING) <= 0)
		{
			compilerMessageCategory = WARNING;
		}
		else
		{
			compilerMessageCategory = ERROR;
		}
		assert compilerMessageCategory != null;


		@NonNls final String filePath = filePath(refElement);

		final String format = format(ENGLISH, "%1$s ('%2$s')", descriptionMessage, filePath); //NON-NLS
		assert format != null;
		projectValidationMessagesRecorder.record(getSmartPsiElementPointer(refElement).getProject(), compilerMessageCategory, format);
	}

	@NotNull
	@NonNls
	private static String filePath(@NotNull final RefElement refElement)
	{
		final SmartPsiElementPointer<?> pointer = getSmartPsiElementPointer(refElement);

		@Nullable final PsiFile psiFile = pointer.getContainingFile();
		if (psiFile == null)
		{
			return "(file)";
		}
		else
		{
			@Nullable final VirtualFile virtualFile = psiFile.getVirtualFile();
			if (virtualFile == null)
			{
				return "(memory)";
			}
			else
			{
				final String filePath = virtualFile.getCanonicalPath();
				assert filePath != null;
				return filePath;
			}
		}
	}

	@NotNull
	private static SmartPsiElementPointer<?> getSmartPsiElementPointer(@NotNull final RefElement refElement)
	{
		final SmartPsiElementPointer<?> pointer = refElement.getPointer();
		assert pointer != null;
		return pointer;
	}
}

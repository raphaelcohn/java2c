package com.java2c.intellij.builder.rebuilding;

import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.ui.TestDialog;
import com.java2c.intellij.builder.validation.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import com.java2c.intellij.useful.UsefulProject;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.ui.Messages.setTestDialog;
import static com.java2c.intellij.builder.rebuilding.NoTestDialog.AnswerAlwaysAsNo;

public final class ProjectOfflineRebuilder
{
	@NotNull
	private final UsefulProject usefulProject;

	public ProjectOfflineRebuilder(@NotNull final UsefulProject usefulProject)
	{
		this.usefulProject = usefulProject;
	}

	public void offlineRebuild(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		final TestDialog oldValue = setTestDialog(AnswerAlwaysAsNo);
		usefulProject.compilerManager.rebuild(new RebuildCompileStatusNotification(projectValidationMessagesRecorder, usefulProject.project)
		{
			@Override
			public void finished(final boolean aborted, final int errors, final int warnings, @NotNull final CompileContext compileContext)
			{
				super.finished(aborted, errors, warnings, compileContext);
				setTestDialog(oldValue);
			}
		});
	}
}

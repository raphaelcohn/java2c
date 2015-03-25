package com.java2c.intellij.builder.validation.projectValidationMessagesRecorders;

import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.compiler.CompilerMessageCategory.ERROR;

public final class ErrorTrackingProjectValidationMessagesRecords implements ProjectValidationMessagesRecorder
{
	@NotNull
	private final ProjectValidationMessagesRecorder delegateTo;

	private boolean hasErrors;

	public ErrorTrackingProjectValidationMessagesRecords(@NotNull final ProjectValidationMessagesRecorder delegateTo)
	{
		this.delegateTo = delegateTo;
		hasErrors = false;
	}

	@Override
	public void record(@NotNull final Project project, @NotNull final CompilerMessageCategory compilerMessageCategory, @NotNull final String message)
	{
		delegateTo.record(project, compilerMessageCategory, message);
		hasErrors = compilerMessageCategory == ERROR;
	}

	public boolean hasErrors()
	{
		return hasErrors;
	}
}

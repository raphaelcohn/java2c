package com.java2c.intellij.projectValidationMessagesRecorders;

import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public interface ProjectValidationMessagesRecorder
{
	void record(@NotNull final Project project, @NotNull final CompilerMessageCategory compilerMessageCategory, @NonNls @NotNull final String message);
}

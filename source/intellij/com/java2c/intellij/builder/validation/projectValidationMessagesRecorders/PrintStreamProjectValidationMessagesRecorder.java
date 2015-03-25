package com.java2c.intellij.builder.validation.projectValidationMessagesRecorders;

import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

import static java.lang.String.format;
import static java.lang.System.err;
import static java.util.Locale.ENGLISH;

public final class PrintStreamProjectValidationMessagesRecorder implements ProjectValidationMessagesRecorder
{
	@SuppressWarnings({"UseOfSystemOutOrSystemErr", "ConstantConditions"})
	@NotNull
	public static final PrintStreamProjectValidationMessagesRecorder StandardErrorPrintStreamProjectValidationMessagesRecorder = new PrintStreamProjectValidationMessagesRecorder(err);

	@NotNull
	private final PrintStream printStream;

	public PrintStreamProjectValidationMessagesRecorder(@NotNull final PrintStream printStream)
	{
		this.printStream = printStream;
	}

	@Override
	public void record(@NotNull final Project project, @NotNull final CompilerMessageCategory compilerMessageCategory, @NotNull final String message)
	{
		final String format = format(ENGLISH, "%1$s:%2$s:%3$s", project.getName(), compilerMessageCategory.name(), message);//NON-NLS
		assert format != null;
		printStream.println(format);
	}
}

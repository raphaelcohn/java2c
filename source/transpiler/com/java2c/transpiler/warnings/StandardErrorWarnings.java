package com.java2c.transpiler.warnings;

import com.java2c.transpiler.javaModules.FatalCompilationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import static com.java2c.utility.EnglishFormatter.FormatterLocale;
import static com.java2c.utility.EnglishFormatter.format;
import static java.lang.System.err;

public final class StandardErrorWarnings implements Warnings
{
	@Override
	public void fatal(@NotNull final Exception cause)
	{
		message("FATAL", cause.getMessage());
	}

	@Override
	public void warn(@NotNull final String warning)
	{
		message("WARN", warning);
	}

	@Override
	public void information(@NotNull final String information)
	{
		message("INFO", information);
	}

	@Override
	public void report(@NotNull final Diagnostic<? extends JavaFileObject> diagnostic)
	{
		final String path = diagnostic.getSource().toUri().getPath();

		final String message = format("'%1$s' in file %2$s at line %3$s column %4$s", diagnostic.getMessage(FormatterLocale), path, diagnostic.getLineNumber(), diagnostic.getColumnNumber());

		switch (diagnostic.getKind())
		{
			case ERROR:
				fatal(new FatalCompilationException(message));
				break;

			case WARNING:
				warn(message);
				break;

			case MANDATORY_WARNING:
				warn(message);
				break;

			case NOTE:
				information(message);
				break;

			case OTHER:
				information(message);
				break;
		}
	}

	@SuppressWarnings({"UseOfSystemOutOrSystemErr", "SynchronizationOnStaticField"})
	private static void message(@NonNls @NotNull final String fatality, @NotNull final String warning)
	{
		synchronized (err)
		{
			err.println(format("%1$s: %2$s", fatality, warning));
		}
	}
}

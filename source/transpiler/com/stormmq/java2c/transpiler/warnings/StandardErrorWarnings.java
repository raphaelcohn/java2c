package com.stormmq.java2c.transpiler.warnings;

import com.stormmq.java2c.transpiler.javaModules.FatalCompilationException;
import org.jetbrains.annotations.NotNull;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import static java.lang.String.format;
import static java.lang.System.err;
import static java.util.Locale.ENGLISH;

public final class StandardErrorWarnings implements Warnings
{
	@NotNull public static final Warnings StandardErrorWarningsInstance = new StandardErrorWarnings();

	private StandardErrorWarnings()
	{
	}

	@Override
	public void fatal(@NotNull final FatalCompilationException cause)
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

		final String message = format(ENGLISH, "'%1$s' in file %2$s at line %3$s column %4$s", diagnostic.getMessage(ENGLISH), path, diagnostic.getLineNumber(), diagnostic.getColumnNumber());

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

	private void message(final String fatality, final String warning)
	{
		synchronized (err)
		{
			err.printf(ENGLISH, "%1$s: %2$s\n", fatality, warning);
		}
	}
}

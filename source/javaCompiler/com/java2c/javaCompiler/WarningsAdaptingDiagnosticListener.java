package com.java2c.javaCompiler;

import com.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NotNull;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

import java.util.Locale;

import static com.java2c.utility.EnglishFormatter.format;

@SuppressWarnings("ClassNamePrefixedWithPackageName")
public final class WarningsAdaptingDiagnosticListener implements DiagnosticListener<JavaFileObject>
{
	@NotNull
	private final Warnings warnings;

	public WarningsAdaptingDiagnosticListener(@NotNull final Warnings warnings)
	{
		this.warnings = warnings;
	}

	@NotNull
	public Locale locale()
	{
		return warnings.locale();
	}

	@Override
	public void report(@NotNull final Diagnostic<? extends JavaFileObject> diagnostic)
	{
		final String path = diagnostic.getSource().toUri().getPath();

		final String message = format("'%1$s' in file %2$s at line %3$s column %4$s", diagnostic.getMessage(warnings.locale()), path, diagnostic.getLineNumber(), diagnostic.getColumnNumber());

		switch (diagnostic.getKind())
		{
			case ERROR:
				warnings.fatal(new FatalCompilationException(message));
				break;

			case WARNING:
				warnings.warn(message);
				break;

			case MANDATORY_WARNING:
				warnings.warn(message);
				break;

			case NOTE:
				warnings.information(message);
				break;

			case OTHER:
				warnings.information(message);
				break;
		}
	}
}

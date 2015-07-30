package com.compilerUser.warnings;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;
import java.util.Locale;

@SuppressWarnings("ClassNamePrefixedWithPackageName")
public interface Warnings
{
	void fatal(@NotNull final Exception cause);

	void warn(@NotNull @NonNls final String warning);

	void information(@NotNull @NonNls final String information);

	@NotNull
	Locale locale();
}

package com.java2c.transpiler.warnings;

import org.jetbrains.annotations.NotNull;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

public interface Warnings extends DiagnosticListener<JavaFileObject>
{
	void fatal(@NotNull final Exception cause);

	void warn(@NotNull final String warning);

	void information(@NotNull final String information);
}

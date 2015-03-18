package com.java2c.transpiler.warnings;

import com.java2c.transpiler.javaModules.FatalCompilationException;
import org.jetbrains.annotations.NotNull;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

public interface Warnings extends DiagnosticListener<JavaFileObject>
{
	void fatal(@NotNull final FatalCompilationException cause);

	void warn(@NotNull final String warning);

	void information(@NotNull final String information);
}

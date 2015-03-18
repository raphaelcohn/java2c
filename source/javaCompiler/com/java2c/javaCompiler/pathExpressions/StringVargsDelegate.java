package com.java2c.javaCompiler.pathExpressions;

import org.jetbrains.annotations.NotNull;

public interface StringVargsDelegate
{
	@SuppressWarnings("unchecked")
	@NotNull
	String execute(@NotNull final String... arguments);
}

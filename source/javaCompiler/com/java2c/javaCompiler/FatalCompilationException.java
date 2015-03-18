package com.java2c.javaCompiler;

import org.jetbrains.annotations.NotNull;

public final class FatalCompilationException extends Exception
{
	public FatalCompilationException(@NotNull final String message)
	{
		super(message);
	}

	public FatalCompilationException(@NotNull final String message, @NotNull final Exception cause)
	{
		super(message, cause);
	}
}

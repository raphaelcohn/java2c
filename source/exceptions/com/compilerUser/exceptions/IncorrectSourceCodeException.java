package com.compilerUser.exceptions;

import org.jetbrains.annotations.NotNull;

public class IncorrectSourceCodeException extends RuntimeException
{
	public IncorrectSourceCodeException(@NotNull final String message)
	{
		super(message);
	}
}

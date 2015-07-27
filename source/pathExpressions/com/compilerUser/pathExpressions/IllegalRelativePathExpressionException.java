package com.compilerUser.pathExpressions;

import org.jetbrains.annotations.NotNull;

public final class IllegalRelativePathExpressionException extends Exception
{
	public IllegalRelativePathExpressionException(@NotNull final String message)
	{
		super(message);
	}
}

package com.compilerUser.exceptions;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public final class ImpossibleStateException extends IllegalStateException
{
	@NotNull
	@NonNls
	private static final String ImpossibleState = "Impossible state";

	public ImpossibleStateException()
	{
		super(ImpossibleState);
	}

	public ImpossibleStateException(@NotNull final Throwable cause)
	{
		super(ImpossibleState, cause);
	}
}

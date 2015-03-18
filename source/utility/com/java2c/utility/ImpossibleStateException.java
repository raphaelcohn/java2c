package com.java2c.utility;

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

	public ImpossibleStateException(@NotNull final Exception cause)
	{
		super(ImpossibleState, cause);
	}
}

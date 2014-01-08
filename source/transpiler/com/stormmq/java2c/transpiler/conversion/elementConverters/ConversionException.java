package com.stormmq.java2c.transpiler.conversion.elementConverters;

import org.jetbrains.annotations.NotNull;

public final class ConversionException extends Exception
{
	public ConversionException(@NotNull final String message)
	{
		super(message);
	}

	public ConversionException(@NotNull final String message, @NotNull final Exception cause)
	{
		super(message, cause);
	}
}

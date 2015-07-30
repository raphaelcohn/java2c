package com.java2c.model.types;

import org.jetbrains.annotations.NotNull;

public final class BanishedException extends RuntimeException
{
	public BanishedException(@NotNull final String methodName)
	{
		super("Method " + methodName + " is Banished");
	}
}

package com.java2c.javaCompiler;

import org.jetbrains.annotations.NotNull;

import static com.java2c.utility.EnglishFormatter.format;

public final class IllegalModuleNameException extends Exception
{
	@SuppressWarnings("HardcodedFileSeparator")
	public IllegalModuleNameException(@NotNull final String moduleName)
	{
		super(format("moduleName (%1$s) must not contain a NUL or a Slash (/)", moduleName));
	}
}

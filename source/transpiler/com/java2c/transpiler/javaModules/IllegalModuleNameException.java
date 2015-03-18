package com.java2c.transpiler.javaModules;

import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class IllegalModuleNameException extends Exception
{
	public IllegalModuleNameException(@NotNull final String moduleName)
	{
		super(format(ENGLISH, "moduleName (%1$s) must not contain a NUL or a Slash (/)", moduleName));
	}
}

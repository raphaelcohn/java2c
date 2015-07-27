package com.compilerUser.pathExpressions;

import com.compilerUser.moduleName.ModuleName;
import org.jetbrains.annotations.NotNull;

import java.nio.file.InvalidPathException;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class IllegalRelativePathException extends Exception
{
	public IllegalRelativePathException(@NotNull final String path, @NotNull final ModuleName moduleName, @NotNull final String moduleRelativeSourcePathExpression)
	{
		super(format(ENGLISH, "Module Relative Source Path (%1$s) formed from moduleName (%2$s) and moduleRelativeSourcePathExpression (%3$s) is absolute", path, moduleRelativeSourcePathExpression, moduleName));
	}

	public IllegalRelativePathException(@NotNull final String path, @NotNull final ModuleName moduleName, @NotNull final String moduleRelativeSourcePathExpression, @NotNull final InvalidPathException cause)
	{
		super(format(ENGLISH, "Module Relative Source Path (%1$s) formed from moduleName (%2$s) and moduleRelativeSourcePathExpression (%3$s) is invalid", path, moduleRelativeSourcePathExpression, moduleName), cause);
	}
}

package com.java2c.javaCompiler.pathExpressions;

import com.java2c.javaCompiler.ModuleName;
import org.jetbrains.annotations.NotNull;

public interface StringVariableArgumentsDelegate
{
	@NotNull
	String execute(@NotNull final ModuleName argument);
}

package com.compilerUser.pathExpressions;

import com.compilerUser.moduleName.ModuleName;
import org.jetbrains.annotations.NotNull;

public interface StringVariableArgumentsDelegate
{
	@NotNull
	String execute(@NotNull final ModuleName argument);
}

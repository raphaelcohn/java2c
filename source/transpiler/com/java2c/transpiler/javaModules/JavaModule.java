package com.java2c.transpiler.javaModules;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public final class JavaModule
{
	@NotNull private final ModuleName moduleName;
	@NotNull private final RootPathAndExpression module;
	@NotNull private final RootPathAndExpression sourceOutput;
	@NotNull private final RootPathAndExpression classOutput;

	public JavaModule(@NotNull final ModuleName moduleName, @NotNull final RootPathAndExpression module, @NotNull final RootPathAndExpression sourceOutput, @NotNull final RootPathAndExpression classOutput) throws IllegalRelativePathException
	{
		this.moduleName = moduleName;
		this.module = module;
		this.sourceOutput = sourceOutput;
		this.classOutput = classOutput;
	}

}

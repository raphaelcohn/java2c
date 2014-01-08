package com.stormmq.java2c.transpiler.javaModules;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public final class JavaModule
{
	@NotNull private final ModuleName moduleName;
	@NotNull private final RootPathAndExpression module;
	@NotNull private final RootPathAndExpression sourceOutput;
	@NotNull private final RootPathAndExpression classOutput;

	// eg 'amqp-parser', '/Users/raphcohn/Documents/java2c/source', 'amqp-parser/src'
	// inside 'amqp-parser/src' there is com/stormmq/amqp/parser/SomeFile.java
	public JavaModule(@NotNull final ModuleName moduleName, @NotNull final RootPathAndExpression module, @NotNull final RootPathAndExpression sourceOutput, @NotNull final RootPathAndExpression classOutput) throws IllegalRelativePathException
	{
		this.moduleName = moduleName;
		this.module = module;
		this.sourceOutput = sourceOutput;
		this.classOutput = classOutput;
	}

	@NotNull
	public Path sourcePath() throws IllegalRelativePathException, FatalCompilationException
	{
		return module.resolvePath(moduleName);
	}

	@NotNull
	public Path sourceOutputPath() throws IllegalRelativePathException, FatalCompilationException
	{
		return sourceOutput.resolvePath(moduleName);
	}

	@NotNull
	public Path classOutputPath() throws IllegalRelativePathException, FatalCompilationException
	{
		return classOutput.resolvePath(moduleName);
	}
}

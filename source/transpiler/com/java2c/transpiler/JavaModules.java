package com.java2c.transpiler;

import com.java2c.transpiler.javaModules.*;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.java2c.transpiler.warnings.StandardErrorWarnings.StandardErrorWarningsInstance;

public final class JavaModules
{
	@NotNull private final List<JavaModule> javaModules;
	@NotNull private final Path[] classPaths;

	public JavaModules(@NotNull final List<ModuleName> moduleNames, @NotNull final RootPathAndExpression moduleRoot, @NotNull final Path[] classPaths, @NotNull final RootPathAndExpression sourceOutput, @NotNull final RootPathAndExpression classOutput) throws IllegalRelativePathException
	{
		this.classPaths = classPaths;
		javaModules = new ArrayList<>(moduleNames.size());
		for (final ModuleName moduleName : moduleNames)
		{
			javaModules.add(new JavaModule(moduleName, moduleRoot, sourceOutput, classOutput));
		}
	}

	public void execute() throws IllegalRelativePathException, FatalCompilationException
	{
		// There will need to be a dependency order in the future...
		for (final JavaModule javaModule : javaModules)
		{
			final Path sourceOutputPath = javaModule.sourceOutputPath();
			final Path classOutputPath = javaModule.classOutputPath();
			final Path sourcePath = javaModule.sourcePath();

			final JavaModuleCompiler javaModuleCompiler = new JavaModuleCompiler(StandardErrorWarningsInstance, sourceOutputPath, classOutputPath, sourcePath, classPaths);
			javaModuleCompiler.compile();
		}
	}
}

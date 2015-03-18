package com.java2c.transpiler.application;

import com.java2c.transpiler.javaModules.*;
import com.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.java2c.utility.EnglishFormatter.format;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.delete;

public final class TranspilerApplication
{
	@NotNull
	@NonNls
	private static final String TemporaryFolderPrefix = "java2c-";

	@NotNull
	private final Warnings warnings;

	@NotNull
	private final List<ModuleName> moduleNames;

	@NotNull
	private final RootPathAndExpression moduleRoot;

	@NotNull
	private final Path[] classPaths;

	@NotNull
	private final RootPathAndExpression sourceOutput;

	@SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
	public TranspilerApplication(@NotNull final Warnings warnings, @NotNull final List<ModuleName> moduleNames, @NotNull final RootPathAndExpression moduleRoot, @NotNull final Path[] classPaths, @NotNull final RootPathAndExpression sourceOutput)
	{
		this.warnings = warnings;
		this.moduleNames = moduleNames;
		this.moduleRoot = moduleRoot;
		this.classPaths = classPaths;
		this.sourceOutput = sourceOutput;
	}

	public void execute()
	{
		final Path classOutputRootPath = createTemporaryClassOutputPath();
		try
		{
			useClassOutputRootPath(classOutputRootPath);
		}
		catch (final IllegalRelativePathExpressionException | IllegalRelativePathException | FatalCompilationException e)
		{
			warnings.fatal(e);
		}
		finally
		{
			try
			{
				delete(classOutputRootPath);
			}
			catch (final IOException ignored)
			{
			}
		}
	}

	private void useClassOutputRootPath(@NotNull final Path classOutputRootPath) throws IllegalRelativePathExpressionException, IllegalRelativePathException, FatalCompilationException
	{
		final RootPathAndExpression classOutput = new RootPathAndExpression(classOutputRootPath, new RelativePathExpression("%m"));

		for (final ModuleName moduleName : moduleNames)
		{
			final Path sourceOutputPath = sourceOutput.resolvePath(moduleName);
			final Path classOutputPath = classOutput.resolvePath(moduleName);
			final Path sourcePath = moduleRoot.resolvePath(moduleName);

			final JavaModuleCompiler javaModuleCompiler = new JavaModuleCompiler(warnings, sourceOutputPath, classOutputPath, sourcePath, classPaths);
			javaModuleCompiler.compile();
		}
	}

	@NotNull
	private static Path createTemporaryClassOutputPath()
	{
		final Path classOutputRootPath;
		try
		{
			classOutputRootPath = createTempDirectory(TemporaryFolderPrefix);
		}
		catch (final IOException e)
		{
			throw new IllegalStateException(format("Could not create a temporary folder for java2c because of '%1$s'", e.getMessage()), e);
		}
		return classOutputRootPath;
	}
}

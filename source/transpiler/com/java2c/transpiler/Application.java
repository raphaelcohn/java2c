package com.java2c.transpiler;

import com.java2c.transpiler.javaModules.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.java2c.transpiler.warnings.StandardErrorWarnings.StandardErrorWarningsInstance;
import static java.lang.String.format;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.delete;
import static java.util.Locale.ENGLISH;

public final class Application
{
	@NotNull private final List<ModuleName> moduleNames;
	@NotNull private final RootPathAndExpression moduleRoot;
	@NotNull private final Path[] classPaths;
	@NotNull private final RootPathAndExpression sourceOutput;

	public Application(@NotNull final List<ModuleName> moduleNames, @NotNull final RootPathAndExpression moduleRoot, @NotNull final Path[] classPaths, @NotNull final RootPathAndExpression sourceOutput)
	{
		this.moduleNames = moduleNames;
		this.moduleRoot = moduleRoot;
		this.classPaths = classPaths;
		this.sourceOutput = sourceOutput;
	}

	public void execute() throws IllegalRelativePathExpressionException, IllegalRelativePathException
	{
		final Path classOutputRootPath;
		try
		{
			classOutputRootPath = createTempDirectory("java2c-");
		}
		catch (IOException e)
		{
			throw new IllegalStateException(format(ENGLISH, "Could not create a temporary folder for java2c because of '%1$s'", e.getMessage()), e);
		}
		try
		{
			final RootPathAndExpression classOutput = new RootPathAndExpression(classOutputRootPath, new RelativePathExpression("%m"));
			new JavaModules(moduleNames, moduleRoot, classPaths, sourceOutput, classOutput).execute();
		}
		catch (FatalCompilationException e)
		{
			StandardErrorWarningsInstance.fatal(e);
		}
		finally
		{
			try
			{
				delete(classOutputRootPath);
			}
			catch (IOException ignored)
			{
			}
		}
	}
}

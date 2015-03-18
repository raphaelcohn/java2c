package com.java2c.transpiler.application;

import com.java2c.javaCompiler.processors.CodeTreeUserAdaptingProcessor;
import com.java2c.transpiler.CFileCreator;
import com.java2c.transpiler.CMaker;
import com.java2c.transpiler.MyCodeTreeUser;
import com.java2c.transpiler.elementConverters.TopLevelInterfaceElementConverter;
import com.java2c.javaCompiler.*;
import com.java2c.javaCompiler.javaSourceFiles.JavaSourceFilesFinder;
import com.java2c.javaCompiler.pathExpressions.IllegalRelativePathException;
import com.java2c.javaCompiler.pathExpressions.IllegalRelativePathExpressionException;
import com.java2c.javaCompiler.pathExpressions.RelativePathExpression;
import com.java2c.javaCompiler.pathExpressions.RootPathAndExpression;
import com.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.tools.JavaCompiler;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.java2c.transpiler.elementConverters.ElementConverter.PackageElementIgnoredElementConverterInstance;
import static com.java2c.transpiler.elementConverters.ElementConverter.TypeElementIgnoredElementConverterInstance;
import static com.java2c.utility.EnglishFormatter.format;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.delete;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

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

	@NotNull
	private final JavaModuleCompiler javaModuleCompiler;

	@SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
	public TranspilerApplication(@NotNull final Warnings warnings, @NotNull final List<ModuleName> moduleNames, @NotNull final RootPathAndExpression moduleRoot, @NotNull final Path[] classPaths, @NotNull final RootPathAndExpression sourceOutput)
	{
		this.warnings = warnings;
		this.moduleNames = moduleNames;
		this.moduleRoot = moduleRoot;
		this.classPaths = classPaths;
		this.sourceOutput = sourceOutput;

		javaModuleCompiler = new JavaModuleCompiler(warnings, new WarningsAdaptingDiagnosticListener(warnings), getJavaCompiler(), new JavaSourceFilesFinder(warnings));
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

			final MyCodeTreeUser codeTreeUser = new MyCodeTreeUser(
					PackageElementIgnoredElementConverterInstance,
					TypeElementIgnoredElementConverterInstance,
					TypeElementIgnoredElementConverterInstance,
					TypeElementIgnoredElementConverterInstance,
					new TopLevelInterfaceElementConverter(new CMaker(new CFileCreator(sourceOutputPath))));
			javaModuleCompiler.compile(classPaths, sourcePath, sourceOutputPath, classOutputPath, new CodeTreeUserAdaptingProcessor(codeTreeUser));
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

	private static JavaCompiler getJavaCompiler()
	{
		@Nullable final JavaCompiler javaCompiler = getSystemJavaCompiler();
		if (javaCompiler == null)
		{
			throw new IllegalStateException("There is no system Java compiler");
		}
		return javaCompiler;
	}
}

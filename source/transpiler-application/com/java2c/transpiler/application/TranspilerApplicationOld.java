package com.java2c.transpiler.application;

import com.compilerUser.exceptions.FatalCompilationException;
import com.compilerUser.moduleCompiler.JavaModuleCompiler;
import com.compilerUser.moduleName.ModuleName;
import com.compilerUser.moduleCompiler.WarningsAdaptingDiagnosticListener;
import com.compilerUser.processors.CodeTreeUserAdaptingProcessor;
import com.java2c.transpiler.TranspilingCodeTreeUser;
import com.compilerUser.javaSourceFiles.JavaSourceFilesFinder;
import com.compilerUser.pathExpressions.IllegalRelativePathException;
import com.compilerUser.pathExpressions.IllegalRelativePathExpressionException;
import com.compilerUser.pathExpressions.RelativePathExpression;
import com.compilerUser.pathExpressions.RootPathAndExpression;
import com.java2c.transpiler.elementHandlers.RootElementHandler;
import com.java2c.transpiler.elementHandlers.PackageElementHandler;
import com.java2c.transpiler.elementHandlers.DispatchingTypeElementHandler;
import com.java2c.transpiler.elementHandlers.typeElementHandlers.AnnotationTypeElementHandler;
import com.java2c.transpiler.elementHandlers.typeElementHandlers.ClassTypeElementHandler;
import com.java2c.transpiler.elementHandlers.typeElementHandlers.EnumTypeElementHandler;
import com.java2c.transpiler.elementHandlers.typeElementHandlers.InterfaceTypeElementHandler;
import com.compilerUser.warnings.Warnings;
import com.compilerUser.exceptions.ImpossibleStateException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.tools.JavaCompiler;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import static com.compilerUser.text.EnglishFormatter.format;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.delete;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public final class TranspilerApplicationOld
{
	@SuppressWarnings("NullableProblems")
	@NotNull
	private static final RelativePathExpression ModuleNamePathExpression;

	static
	{
		try
		{
			ModuleNamePathExpression = new RelativePathExpression("%m");
		}
		catch (final IllegalRelativePathExpressionException ignored)
		{
			throw new ImpossibleStateException();
		}
	}

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
	private final RootPathAndExpression sourceOutput;

	@NotNull
	private final Collection<Path> additionalClassPath;

	@NotNull
	private final JavaModuleCompiler javaModuleCompiler;

	@NotNull
	private final CodeTreeUserAdaptingProcessor processor;

	@SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
	public TranspilerApplicationOld(@NotNull final Warnings warnings, @NotNull final List<ModuleName> moduleNames, @NotNull final RootPathAndExpression moduleRoot, @NotNull final RootPathAndExpression sourceOutput, @NotNull final Collection<Path> additionalClassPath)
	{
		this.warnings = warnings;
		this.moduleNames = moduleNames;
		this.moduleRoot = moduleRoot;
		this.sourceOutput = sourceOutput;
		this.additionalClassPath = additionalClassPath;

		javaModuleCompiler = new JavaModuleCompiler(warnings, new WarningsAdaptingDiagnosticListener(warnings), getJavaCompiler(), new JavaSourceFilesFinder(warnings));

		processor = new CodeTreeUserAdaptingProcessor(new TranspilingCodeTreeUser(new RootElementHandler(new PackageElementHandler(), new DispatchingTypeElementHandler(new AnnotationTypeElementHandler(), new InterfaceTypeElementHandler(), new EnumTypeElementHandler(), new ClassTypeElementHandler()))));
	}

	public void execute()
	{
		final Path classOutputRootPath = createTemporaryClassOutputPath();
		try
		{
			useClassOutputRootPath(classOutputRootPath);
		}
		catch (final IllegalRelativePathException | FatalCompilationException e)
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

	private void useClassOutputRootPath(@NotNull final Path classOutputRootPath) throws IllegalRelativePathException, FatalCompilationException
	{
		final RootPathAndExpression classOutput = new RootPathAndExpression(classOutputRootPath, ModuleNamePathExpression);

		for (final ModuleName moduleName : moduleNames)
		{
			final Path sourceOutputPath = sourceOutput.resolvePath(moduleName);
			final Path classOutputPath = classOutput.resolvePath(moduleName);
			final Path sourcePath = moduleRoot.resolvePath(moduleName);

			// TODO: XXX add all modules to source, or how else to manage dependencies?;

			javaModuleCompiler.compile(additionalClassPath, sourcePath, sourceOutputPath, classOutputPath, processor);
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

	@NotNull
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

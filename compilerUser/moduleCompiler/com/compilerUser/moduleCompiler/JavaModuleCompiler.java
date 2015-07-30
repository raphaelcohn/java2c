package com.compilerUser.moduleCompiler;

import com.compilerUser.exceptions.FatalCompilationException;
import com.compilerUser.javaSourceFiles.JavaSourceFilesFinder;
import com.compilerUser.warnings.Warnings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.processing.Processor;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static com.compilerUser.text.CharsetHelper.Utf8;
import static java.lang.Boolean.TRUE;

public final class JavaModuleCompiler
{
	@NotNull
	private final JavaCompiler javaCompiler;

	@NotNull
	private final Warnings warnings;

	@NotNull
	private final WarningsAdaptingDiagnosticListener diagnosticListener;

	@NotNull
	private final JavaSourceFilesFinder javaSourceFilesFinder;

	public JavaModuleCompiler(@NotNull final Warnings warnings, @NotNull final WarningsAdaptingDiagnosticListener diagnosticListener, @NotNull final JavaCompiler javaCompiler, @NotNull final JavaSourceFilesFinder javaSourceFilesFinder)
	{
		this.javaCompiler = javaCompiler;
		this.warnings = warnings;
		this.diagnosticListener = diagnosticListener;
		this.javaSourceFilesFinder = javaSourceFilesFinder;
	}

	public void compile(@NotNull final Collection<Path> additionalClassPath, @NotNull final Path sourcePath, @NotNull final Path sourceOutputPath, @NotNull final Path classOutputPath, @NotNull final Processor processor) throws FatalCompilationException
	{
		final StandardJavaFileManager standardJavaFileManager = createStandardJavaFileManager(additionalClassPath, sourcePath, sourceOutputPath, classOutputPath);
		final Iterable<? extends JavaFileObject> compilationUnits = findCompilationUnits(standardJavaFileManager, sourcePath);
		final StringWriter additionalCompilerOutput = new StringWriter(4096);

		@Nullable final Boolean compilationSucceeded = createCompilationTask(standardJavaFileManager, compilationUnits, additionalCompilerOutput, processor).call();

		warnings.information(additionalCompilerOutput.toString());

		if (compilationSucceeded != null && compilationSucceeded.equals(TRUE))
		{
			warnings.information("Compilation Succeeded");
		}
		else
		{
			throw new FatalCompilationException("Compilation Failed");
		}
	}

	@NotNull
	private StandardJavaFileManager createStandardJavaFileManager(@NotNull final Collection<Path> additionalClassPath, @NotNull final Path sourcePath, @NotNull final Path sourceOutputPath, @NotNull final Path classOutputPath) throws FatalCompilationException
	{
		@NotNull final StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(diagnosticListener, diagnosticListener.locale(), Utf8);

		new StandardJavaFileManagerConfigurator(fileManager)
			.setDefaultPlatformClassPath()
			.setDefaultAnnotationProcessorPath()
			.addAdditionalClassPaths(additionalClassPath)
			.setSourcePaths(sourcePath)
			.setSourceOutputPath(sourceOutputPath)
			.setClassOutputPath(classOutputPath);

		return fileManager;
	}

	// Apparently, we can get 'Trees' for this.
	@NotNull
	private CompilationTask createCompilationTask(@NotNull final JavaFileManager standardJavaFileManager, @NotNull final Iterable<? extends JavaFileObject> compilationUnits, @NotNull final Writer additionalCompilerOutput, @NotNull final Processor processor)
	{
		final Iterable<String> compilerOptions = null;
		final Iterable<String> namesOfClassesToBeProcessedByAnnotationProcessing = null;
		@NotNull final CompilationTask task = javaCompiler.getTask(additionalCompilerOutput, standardJavaFileManager, diagnosticListener, compilerOptions, namesOfClassesToBeProcessedByAnnotationProcessing, compilationUnits);

		task.setLocale(diagnosticListener.locale());

		final Collection<Processor> processors = new ArrayList<>(1);
		processors.add(processor);
		task.setProcessors(processors);

		return task;
	}

	@NotNull
	private Iterable<? extends JavaFileObject> findCompilationUnits(@NotNull final StandardJavaFileManager standardJavaFileManager, @NotNull final Path sourcePath) throws FatalCompilationException
	{
		return standardJavaFileManager.getJavaFileObjectsFromFiles(javaSourceFilesFinder.find(sourcePath));
	}
}

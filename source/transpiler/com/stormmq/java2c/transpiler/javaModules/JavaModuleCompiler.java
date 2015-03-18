package com.stormmq.java2c.transpiler.javaModules;

import com.stormmq.java2c.transpiler.annotationProcessors.CodeTreeAnalyzerProcessor;
import com.stormmq.java2c.transpiler.conversion.CFileCreator;
import com.stormmq.java2c.transpiler.conversion.CMaker;
import com.stormmq.java2c.transpiler.conversion.elementConverters.TopLevelInterfaceElementConverter;
import com.stormmq.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.processing.Processor;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.stormmq.java2c.transpiler.CharsetHelper.Utf8;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.ElementConverter.PackageElementIgnoredElementConverterInstance;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.ElementConverter.TypeElementIgnoredElementConverterInstance;
import static java.lang.String.format;
import static java.nio.file.Files.*;
import static java.util.Locale.ENGLISH;
import static javax.tools.StandardLocation.*;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public final class JavaModuleCompiler
{
	@NotNull private final JavaCompiler javaCompiler;
	@NotNull private final Locale locale;
	@NotNull private final Path sourcePath;
	@NotNull private final Warnings warnings;
	@NotNull private final StandardJavaFileManager fileManager;
	@NotNull private final CMaker cMaker;

	public JavaModuleCompiler(@NotNull final Warnings warnings, @NotNull final Path sourceOutputPath, @NotNull final Path classOutputPath, @NotNull final Path sourcePath, @NotNull final Path... classPaths) throws FatalCompilationException
	{
		@Nullable final JavaCompiler javaCompiler = getSystemJavaCompiler();
		if (javaCompiler == null)
		{
			throw new IllegalStateException("There is no system compiler");
		}
		this.javaCompiler = javaCompiler;
		this.locale = ENGLISH;
		this.sourcePath = sourcePath;
		this.warnings = warnings;
		fileManager = javaCompiler.getStandardFileManager(this.warnings, locale, Utf8);
		cMaker = new CMaker(new CFileCreator(sourceOutputPath));

		setDefaultLocation(ANNOTATION_PROCESSOR_PATH);
		setDefaultLocation(PLATFORM_CLASS_PATH);
		setLocation(CLASS_PATH, classPaths);
		setLocation(SOURCE_PATH, sourcePath);
		setLocation(SOURCE_OUTPUT, sourceOutputPath);
		setLocation(CLASS_OUTPUT, classOutputPath);
	}

	public void compile() throws FatalCompilationException
	{
		final List<File> javaSourceFiles = javaSourceFiles();
		final Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(javaSourceFiles);

		final Writer additionalCompilerOutput = new StringWriter(4096);
		final Iterable<String> compilerOptions = null;
		final Iterable<String> namesOfClassesToBeProcessedByAnnotationProcessing = null;
		final JavaCompiler.CompilationTask task = javaCompiler.getTask(additionalCompilerOutput, fileManager, warnings, compilerOptions, namesOfClassesToBeProcessedByAnnotationProcessing, compilationUnits);

		task.setLocale(locale);

		task.setProcessors(new ArrayList<Processor>(1)
		{{
			new CodeTreeAnalyzerProcessor
			(
				warnings,
				PackageElementIgnoredElementConverterInstance,
				TypeElementIgnoredElementConverterInstance,
				TypeElementIgnoredElementConverterInstance,
				TypeElementIgnoredElementConverterInstance,
				new TopLevelInterfaceElementConverter(cMaker)
			).register(this);
		}});

		final boolean succeeded = task.call();
		final String anyOtherMessages = additionalCompilerOutput.toString();
		warnings.information(anyOtherMessages);
		if (succeeded)
		{
			warnings.information("Compilation Succeeded");
		}
		else
		{
			throw new FatalCompilationException("Compilation Failed");
		}
	}

	private List<File> javaSourceFiles() throws FatalCompilationException
	{
		final List<Path> javaSources = new ArrayList<>(4096);
		try
		{
			walkFileTree(sourcePath, new StatefulJavaSourceFileVisitor(warnings, javaSources));
		}
		catch (IOException e)
		{
			throw new FatalCompilationException(format(ENGLISH, "Ignoring entire contents of path %1$s because of '%2$s'", sourcePath, e.getMessage()));
		}

		final List<File> javaSourceFiles = new ArrayList<>(javaSources.size());
		for (final Path javaSource : javaSources)
		{
			javaSourceFiles.add(javaSource.toFile());
		}
		return javaSourceFiles;
	}

	private void setDefaultLocation(@NotNull final StandardLocation location)
	{
		try
		{
			fileManager.setLocation(location, null);
		}
		catch (IOException e)
		{
			throw new IllegalStateException("Should never happen", e);
		}
	}

	private void setLocation(@NotNull final StandardLocation location, @NotNull final Path... sourcePaths) throws FatalCompilationException
	{
		try
		{
			fileManager.setLocation(location, toLocation(location.isOutputLocation(), sourcePaths));
		}
		catch (IOException e)
		{
			throw new FatalCompilationException(format(ENGLISH, "Paths '%1$s' are not writable or does not exist", Arrays.toString(sourcePaths)));
		}
	}

	@NotNull
	private static Iterable<? extends File> toLocation(final boolean needsToBeWritable, @NotNull final Path... sourcePaths) throws FatalCompilationException
	{
		final int length = sourcePaths.length;
		final List<File> files = new ArrayList<>(length);
		for (final Path path : sourcePaths)
		{
			if (exists(path))
			{
				final Path absoluteResolvedPath;
				try
				{
					absoluteResolvedPath = path.toRealPath();
				}
				catch (IOException e)
				{
					throw new FatalCompilationException(format(ENGLISH, "Could not resolved path '%1$s' because of '%2$s'", path, e.getMessage()));
				}
				if (!isReadable(absoluteResolvedPath))
				{
					throw new FatalCompilationException(format(ENGLISH, "Path '%1$s' is not readable", path));
				}
				if (needsToBeWritable)
				{
					if (!isWritable(absoluteResolvedPath))
					{
						throw new FatalCompilationException(format(ENGLISH, "Path '%1$s' is not writable", path));
					}
					if (!isDirectory(absoluteResolvedPath))
					{
						throw new FatalCompilationException(format(ENGLISH, "Path '%1$s' is not a directory", path));
					}
				}
				else
				{
					if (!isDirectory(absoluteResolvedPath))
					{
						if (!isRegularFile(absoluteResolvedPath))
						{
							throw new FatalCompilationException(format(ENGLISH, "Path '%1$s' is not a directory or regular file", path));
						}
						final String fileName = absoluteResolvedPath.getFileName().toString();
						if (!(fileName.endsWith(".zip") || fileName.endsWith(".jar")))
						{
							throw new FatalCompilationException(format(ENGLISH, "Path '%1$s' is not a directory, zip or jar file", path));
						}
					}
				}
			}
			else
			{
				if (needsToBeWritable)
				{
					final String fileName = path.getFileName().toString();
					if (fileName.endsWith(".zip") || fileName.endsWith(".jar"))
					{
						throw new FatalCompilationException(format(ENGLISH, "Path '%1$s' should not be a zip or jar file", path));
					}
					try
					{
						createDirectories(path);
					}
					catch (IOException e)
					{
						throw new FatalCompilationException(format(ENGLISH, "Path '%1$s' could not be created because of '%2$s'", path, e.getMessage()));
					}
				}
			}

			files.add(path.toFile());
		}
		return files;
	}
}

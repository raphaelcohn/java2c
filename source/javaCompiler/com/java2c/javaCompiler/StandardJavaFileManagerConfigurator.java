package com.java2c.javaCompiler;

import com.java2c.utility.ImpossibleStateException;
import org.jetbrains.annotations.NotNull;

import javax.tools.JavaFileManager.Location;
import javax.tools.StandardJavaFileManager;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import static com.java2c.javaCompiler.SourcePathsHelper.sourcePathsToIterableFiles;
import static com.java2c.utility.EnglishFormatter.format;
import static javax.tools.StandardLocation.*;

public class StandardJavaFileManagerConfigurator
{
	@NotNull
	private final StandardJavaFileManager fileManager;

	public StandardJavaFileManagerConfigurator(@NotNull final StandardJavaFileManager fileManager)
	{
		this.fileManager = fileManager;
	}

	@NotNull
	public final StandardJavaFileManagerConfigurator setDefaultAnnotationProcessorPath()
	{
		return setDefaultLocation(ANNOTATION_PROCESSOR_PATH);
	}

	@NotNull
	public final StandardJavaFileManagerConfigurator setDefaultPlatformClassPath()
	{
		return setDefaultLocation(PLATFORM_CLASS_PATH);
	}

	@NotNull
	public final StandardJavaFileManagerConfigurator setClassPaths(@NotNull final Path... classPaths) throws FatalCompilationException
	{
		return setLocation(CLASS_PATH, classPaths);
	}

	@NotNull
	public final StandardJavaFileManagerConfigurator setSourcePaths(@NotNull final Path... sourcePaths) throws FatalCompilationException
	{
		return setLocation(SOURCE_PATH, sourcePaths);
	}

	@NotNull
	public final StandardJavaFileManagerConfigurator setSourceOutputPath(@NotNull final Path sourceOutputPath) throws FatalCompilationException
	{
		return setLocation(SOURCE_PATH, sourceOutputPath);
	}

	@NotNull
	public final StandardJavaFileManagerConfigurator setClassOutputPath(@NotNull final Path classOutputPath) throws FatalCompilationException
	{
		return setLocation(CLASS_PATH, classOutputPath);
	}

	@NotNull
	private StandardJavaFileManagerConfigurator setDefaultLocation(@NotNull final Location location)
	{
		try
		{
			fileManager.setLocation(location, null);
		}
		catch (final IOException e)
		{
			throw new ImpossibleStateException(e);
		}
		return this;
	}

	@NotNull
	private StandardJavaFileManagerConfigurator setLocation(@NotNull final Location location, @NotNull final Path... sourcePaths) throws FatalCompilationException
	{
		try
		{
			fileManager.setLocation(location, sourcePathsToIterableFiles(location.isOutputLocation(), sourcePaths));
		}
		catch (final IOException e)
		{
			throw new FatalCompilationException(format("Paths '%1$s' are not writable or does not exist", Arrays.toString(sourcePaths)), e);
		}
		return this;
	}
}

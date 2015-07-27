package com.compilerUser.moduleCompiler;

import com.compilerUser.exceptions.FatalCompilationException;
import com.compilerUser.exceptions.ImpossibleStateException;
import org.jetbrains.annotations.NotNull;

import javax.tools.JavaFileManager.Location;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static com.compilerUser.moduleCompiler.SourcePathsHelper.sourcePathsToIterableFiles;
import static com.compilerUser.text.EnglishFormatter.format;
import static javax.tools.StandardLocation.*;

public final class StandardJavaFileManagerConfigurator
{
	@NotNull
	private final StandardJavaFileManager fileManager;

	public StandardJavaFileManagerConfigurator(@NotNull final StandardJavaFileManager fileManager)
	{
		this.fileManager = fileManager;
	}

	@NotNull
	public StandardJavaFileManagerConfigurator setDefaultAnnotationProcessorPath()
	{
		return setDefaultLocation(ANNOTATION_PROCESSOR_PATH);
	}

	@NotNull
	public StandardJavaFileManagerConfigurator setDefaultPlatformClassPath()
	{
		return setDefaultLocation(PLATFORM_CLASS_PATH);
	}

	@NotNull
	public StandardJavaFileManagerConfigurator addAdditionalClassPaths(@NotNull final Collection<Path> additionalClassPath) throws FatalCompilationException
	{
		setDefaultLocation(CLASS_PATH);

		final Set<File> allFilePaths = new LinkedHashSet<>(additionalClassPath.size());
		for (final File initialFilePath : fileManager.getLocation(CLASS_PATH))
		{
			allFilePaths.add(initialFilePath);
		}
		allFilePaths.addAll(sourcePathsToIterableFiles(false, additionalClassPath.toArray(new Path[additionalClassPath.size()])));

		try
		{
			fileManager.setLocation(CLASS_PATH, allFilePaths);
		}
		catch (final IOException e)
		{
			throw new FatalCompilationException(format("Class paths '%1$s' are not writable or does not exist", allFilePaths), e);
		}

		return this;
	}

	@NotNull
	public StandardJavaFileManagerConfigurator setSourcePaths(@NotNull final Path... sourcePaths) throws FatalCompilationException
	{
		return setLocation(SOURCE_PATH, sourcePaths);
	}

	@NotNull
	public StandardJavaFileManagerConfigurator setSourceOutputPath(@NotNull final Path sourceOutputPath) throws FatalCompilationException
	{
		return setLocation(SOURCE_OUTPUT, sourceOutputPath);
	}

	@NotNull
	public StandardJavaFileManagerConfigurator setClassOutputPath(@NotNull final Path classOutputPath) throws FatalCompilationException
	{
		return setLocation(CLASS_OUTPUT, classOutputPath);
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
			final Collection<File> paths = sourcePathsToIterableFiles(location.isOutputLocation(), sourcePaths);
			fileManager.setLocation(location, paths);
		}
		catch (final IOException e)
		{
			throw new FatalCompilationException(format("Paths '%1$s' are not writable or does not exist", Arrays.toString(sourcePaths)), e);
		}
		return this;
	}
}

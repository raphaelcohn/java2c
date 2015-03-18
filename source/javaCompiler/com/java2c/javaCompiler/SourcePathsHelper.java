package com.java2c.javaCompiler;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static com.java2c.utility.EnglishFormatter.format;
import static java.nio.file.Files.*;

public final class SourcePathsHelper
{
	private SourcePathsHelper()
	{
	}

	@NotNull
	public static Iterable<? extends File> sourcePathsToIterableFiles(final boolean needsToBeWritable, @NotNull final Path... sourcePaths) throws FatalCompilationException
	{
		final int length = sourcePaths.length;
		final Collection<File> files = new ArrayList<>(length);
		for (final Path path : sourcePaths)
		{
			if (exists(path))
			{
				final Path absoluteResolvedPath;
				try
				{
					absoluteResolvedPath = path.toRealPath();
				}
				catch (final IOException e)
				{
					throw new FatalCompilationException(format("Could not resolved path '%1$s' because of '%2$s'", path, e.getMessage()), e);
				}
				if (!isReadable(absoluteResolvedPath))
				{
					throw new FatalCompilationException(format("Path '%1$s' is not readable", path));
				}
				if (needsToBeWritable)
				{
					if (!isWritable(absoluteResolvedPath))
					{
						throw new FatalCompilationException(format("Path '%1$s' is not writable", path));
					}
					if (!isDirectory(absoluteResolvedPath))
					{
						throw new FatalCompilationException(format("Path '%1$s' is not a directory", path));
					}
				}
				else
				{
					if (!isDirectory(absoluteResolvedPath))
					{
						if (!isRegularFile(absoluteResolvedPath))
						{
							throw new FatalCompilationException(format("Path '%1$s' is not a directory or regular file", path));
						}
						if (!isPathAZipOrJarFile(absoluteResolvedPath))
						{
							throw new FatalCompilationException(format("Path '%1$s' is not a directory, zip or jar file", path));
						}
					}
				}
			}
			else
			{
				if (needsToBeWritable)
				{
					if (isPathAZipOrJarFile(path))
					{
						throw new FatalCompilationException(format("Path '%1$s' should not be a zip or jar file", path));
					}
					try
					{
						createDirectories(path);
					}
					catch (final IOException e)
					{
						throw new FatalCompilationException(format("Path '%1$s' could not be created because of '%2$s'", path, e.getMessage()), e);
					}
				}
			}

			files.add(path.toFile());
		}
		return files;
	}

	private static boolean isPathAZipOrJarFile(@NotNull final Path path)
	{
		return isFileNameAZipOrJar(getFileName(path));
	}

	private static String getFileName(@NotNull final Path path)
	{
		return path.getFileName().toString();
	}

	private static boolean isFileNameAZipOrJar(@NotNull @NonNls final String fileName)
	{
		return fileName.endsWith(".zip") || fileName.endsWith(".jar");
	}
}

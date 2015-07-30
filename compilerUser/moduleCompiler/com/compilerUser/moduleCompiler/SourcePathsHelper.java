package com.compilerUser.moduleCompiler;

import com.compilerUser.exceptions.FatalCompilationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static com.compilerUser.text.EnglishFormatter.format;
import static java.nio.file.Files.*;

public final class SourcePathsHelper
{
	private SourcePathsHelper()
	{
	}

	@NotNull
	public static Collection<File> sourcePathsToIterableFiles(final boolean needsToBeWritable, @NotNull final Path... realPaths) throws FatalCompilationException
	{
		final int length = realPaths.length;
		final Collection<File> files = new ArrayList<>(length);
		for (final Path realPath : realPaths)
		{
			if (exists(realPath))
			{
				if (!isReadable(realPath))
				{
					throw new FatalCompilationException(format("Path '%1$s' is not readable", realPath));
				}
				if (needsToBeWritable)
				{
					if (!isWritable(realPath))
					{
						throw new FatalCompilationException(format("Path '%1$s' is not writable", realPath));
					}
					if (!isDirectory(realPath))
					{
						throw new FatalCompilationException(format("Path '%1$s' is not a directory", realPath));
					}
				}
				else
				{
					if (!isDirectory(realPath))
					{
						if (!isRegularFile(realPath))
						{
							throw new FatalCompilationException(format("Path '%1$s' is not a directory or regular file", realPath));
						}
						if (!isPathAZipOrJarFile(realPath))
						{
							throw new FatalCompilationException(format("Path '%1$s' is not a directory, zip or jar file", realPath));
						}
					}
				}
			}
			else
			{
				if (needsToBeWritable)
				{
					if (isPathAZipOrJarFile(realPath))
					{
						throw new FatalCompilationException(format("Path '%1$s' should not be a zip or jar file", realPath));
					}
					try
					{
						createDirectories(realPath);
					}
					catch (final IOException e)
					{
						throw new FatalCompilationException(format("Path '%1$s' could not be created because of '%2$s'", realPath, e.getMessage()), e);
					}
				}
			}

			files.add(realPath.toFile());
		}
		return files;
	}

	private static boolean isPathAZipOrJarFile(@NotNull final Path path)
	{
		return isFileNameAZipOrJar(getFileName(path));
	}

	@NotNull
	private static String getFileName(@NotNull final Path path)
	{
		return path.getFileName().toString();
	}

	private static boolean isFileNameAZipOrJar(@NotNull @NonNls final String fileName)
	{
		return fileName.endsWith(".zip") || fileName.endsWith(".jar");
	}
}

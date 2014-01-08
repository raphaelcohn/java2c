package com.stormmq.java2c.transpiler.conversion;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static java.lang.String.format;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;
import static java.util.Locale.ENGLISH;

public final class CFileCreator
{
	@NotNull private final Path sourceOutputPath;

	public CFileCreator(@NotNull final Path sourceOutputPath)
	{
		this.sourceOutputPath = sourceOutputPath;
	}

	@NotNull
	public File headerFilePath(@NotNull final String qualifiedName, final boolean isPackage) throws IOException
	{
		return filePath(qualifiedName, isPackage, "h");
	}

	@NotNull
	public File sourceFilePath(@NotNull final String qualifiedName, final boolean isPackage) throws IOException
	{
		return filePath(qualifiedName, isPackage, "c");
	}

	@NotNull
	private File filePath(@NotNull final String qualifiedName, final boolean isPackage, @NotNull final String extension) throws IOException
	{
		final String fileName = format(ENGLISH, "%1$s%2$s.%3$s", qualifiedName, isPackage ? "-package" : "", extension);
		final Path resolved = sourceOutputPath.resolve(get(fileName));
		if (!exists(resolved))
		{
			createFile(resolved);
		}
		return resolved.toFile();
	}
}

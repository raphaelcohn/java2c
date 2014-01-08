package com.stormmq.java2c.transpiler.javaModules;

import com.stormmq.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static java.lang.String.format;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;
import static java.util.Locale.ENGLISH;

public final class StatefulJavaSourceFileVisitor implements FileVisitor<Path>
{
	@NotNull private final Warnings warnings;
	@NotNull private final List<Path> javaSources;

	public StatefulJavaSourceFileVisitor(@NotNull final Warnings warnings, @NotNull final List<Path> javaSources)
	{
		this.warnings = warnings;
		this.javaSources = javaSources;
	}

	@Override
	public FileVisitResult preVisitDirectory(@NotNull final Path dir, @SuppressWarnings("SpellCheckingInspection") @NotNull final BasicFileAttributes attrs)
	{
		final String folderName = dir.getFileName().toString();
		if (attrs.isSymbolicLink())
		{
			warnings.warn(format(ENGLISH, "Ignoring folder named '%1$s' because it is a symbolic link (in path %2$s)", folderName, dir));
			return SKIP_SUBTREE;
		}
		if (!attrs.isDirectory())
		{
			warnings.warn(format(ENGLISH, "Ignoring folder named '%1$s' because it is not a directory (in path %2$s)", folderName, dir));
			return SKIP_SUBTREE;
		}
		if (folderName.charAt(0) == '.')
		{
			warnings.warn(format(ENGLISH, "Ignoring folder named '%1$s' because it is hidden (in path %2$s)", folderName, dir));
			return SKIP_SUBTREE;
		}
		for(int index = 0; index < folderName.length(); index++)
		{
			final char character = folderName.charAt(index);

			if (character > 127)
			{
				warnings.warn(format(ENGLISH, "Ignoring folder named '%1$s' because it contains a non US-ASCII character at index %2$s (in path %3$s)", folderName, index, dir));
				return SKIP_SUBTREE;
			}
			if (character == '-')
			{
				warnings.warn(format(ENGLISH, "Ignoring folder named '%1$s' because it contains a hyphen at index %2$s (in path %3$s)", folderName, index, dir));
				return SKIP_SUBTREE;
			}
			// 0 - 9
			if (character >= 48 && character <= 57)
			{
				continue;
			}
			// A - Z
			if (character >= 65 && character <= 90)
			{
				continue;
			}
			// a - z
			if (character >= 97 && character <= 122)
			{
				continue;
			}
			warnings.warn(format(ENGLISH, "Ignoring folder named '%1$s' because it contains a character that is not alphanumeric at index %2$s (in path %3$s)", folderName, index, dir));
			return SKIP_SUBTREE;
		}
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(@NotNull final Path file, @SuppressWarnings("SpellCheckingInspection") @NotNull final BasicFileAttributes attrs)
	{
		final String fileName = file.getFileName().toString();
		if (!attrs.isRegularFile() || attrs.isSymbolicLink())
		{
			warnings.warn(format(ENGLISH, "Ignoring file named '%1$s' because it is not a regular file (in path %2$s)", fileName, file));
			return CONTINUE;
		}
		if (!fileName.endsWith(".java"))
		{
			warnings.warn(format(ENGLISH, "Ignoring file named '%1$s' because it is not a java file (in path %2$s)", fileName, file));
			return CONTINUE;
		}

		// Special case file hyphen in name!
		if (fileName.equals("package-info.java"))
		{
			javaSources.add(file);
			return CONTINUE;
		}

		boolean periodEncountered = false;
		for(int index = 0; index < fileName.length(); index++)
		{
			final char character = fileName.charAt(index);

			if (character > 127)
			{
				warnings.warn(format(ENGLISH, "Ignoring file named '%1$s' because it contains a non US-ASCII character at index %2$s (in path %3$s)", fileName, index, file));
				return SKIP_SUBTREE;
			}
			if (character == '-')
			{
				warnings.warn(format(ENGLISH, "Ignoring file named '%1$s' because it contains a hyphen at index %2$s (in path %3$s)", fileName, index, file));
				return SKIP_SUBTREE;
			}
			if (character == '.')
			{
				if (periodEncountered)
				{
					warnings.warn(format(ENGLISH, "Ignoring file named '%1$s' because it contains a second period at index %2$s (in path %3$s)", fileName, index, file));
					return SKIP_SUBTREE;
				}
				periodEncountered = true;
				continue;
			}
			// 0 - 9
			if (character >= 48 && character <= 57)
			{
				continue;
			}
			// A - Z
			if (character >= 65 && character <= 90)
			{
				continue;
			}
			// a - z
			if (character >= 97 && character <= 122)
			{
				continue;
			}
			warnings.warn(format(ENGLISH, "Ignoring file named '%1$s' because it contains a character that is not alphanumeric at index %2$s (in path %3$s)", fileName, index, file));
			return CONTINUE;
		}

		javaSources.add(file);
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(@NotNull final Path file, @NotNull final IOException exc)
	{
		final String fileName = file.getFileName().toString();
		warnings.warn(format(ENGLISH, "Ignoring file named '%1$s' because it could not be opened because of %2$s (in path %3$s)", fileName, exc.getMessage(), file));
		return CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(@NotNull final Path dir, @Nullable final IOException exc)
	{
		return CONTINUE;
	}
}

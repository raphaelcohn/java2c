package com.compilerUser.javaSourceFiles;

import com.compilerUser.warnings.Warnings;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.compilerUser.text.EnglishFormatter.format;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

public final class StatefulJavaSourceFileVisitor implements FileVisitor<Path>
{
	@NotNull
	private final Warnings warnings;

	@NotNull
	private final JavaSourceFileUser javaSourceFileUser;

	public StatefulJavaSourceFileVisitor(@NotNull final Warnings warnings, @NotNull final JavaSourceFileUser javaSourceFileUser)
	{
		this.warnings = warnings;
		this.javaSourceFileUser = javaSourceFileUser;
	}

	@NotNull
	@Override
	public FileVisitResult preVisitDirectory(@NotNull final Path dir, @SuppressWarnings("SpellCheckingInspection") @NotNull final BasicFileAttributes attrs)
	{
		final String folderName = fileName(dir);

		if (attrs.isSymbolicLink())
		{
			return ignoreDirectory(dir, folderName, "because it is a symbolic link");
		}

		if (!attrs.isDirectory())
		{
			return ignoreDirectory(dir, folderName, "because it is not a directory");
		}

		if (isCharacterPeriod(folderName.charAt(0)))
		{
			return ignoreDirectory(dir, folderName, "because it is hidden");
		}

		final int length = folderName.length();
		for(int index = 0; index < length; index++)
		{
			final char character = folderName.charAt(index);

			if (isCharacterNonUsAscii(character))
			{
				return ignoreDirectory(dir, folderName, "because the folder name contains a non US-ASCII character", index);
			}

			if (isCharacterHyphen(character))
			{
				return ignoreDirectory(dir, folderName, "because the folder name contains a hyphen", index);
			}

			if (isCharacterAlphanumeric(character))
			{
				return ignoreDirectory(dir, folderName, "because the folder name contains a character that is not alphanumeric", index);
			}
		}

		return CONTINUE;
	}

	@NotNull
	@Override
	public FileVisitResult visitFile(@NotNull final Path file, @SuppressWarnings("SpellCheckingInspection") @NotNull final BasicFileAttributes attrs)
	{
		@NonNls final String fileName = fileName(file);

		if (attrs.isSymbolicLink())
		{
			return ignoreFile(file, fileName, "because it is not a symbolic link");
		}

		if (!attrs.isRegularFile())
		{
			return ignoreFile(file, fileName, "because it is not a regular file");
		}

		if (!fileName.endsWith(".java"))
		{
			// Special case: IntelliJ module files
			if (fileName.endsWith(".iml"))
			{
				return CONTINUE;
			}
			return ignoreFile(file, fileName, "because it is not a java file");
		}

		// Special case: Only java file with a hyphen permitted in its name
		if ("package-info.java".equals(fileName))
		{
			return useFile(file);
		}

		boolean periodEncountered = false;
		final int length = fileName.length();
		for(int index = 0; index < length; index++)
		{
			final char character = fileName.charAt(index);

			if (isCharacterNonUsAscii(character))
			{
				return ignoreFile(file, fileName, "because the file name contains a non US-ASCII character", index);
			}

			if (isCharacterHyphen(character))
			{
				return ignoreFile(file, fileName, "because the file name contains a hyphen", index);
			}

			if (isCharacterPeriod(character))
			{
				if (periodEncountered)
				{
					return ignoreFile(file, fileName, "because it contains a second period", index);
				}
				periodEncountered = true;
				continue;
			}

			if (isCharacterAlphanumeric(character))
			{
				return ignoreFile(file, fileName, "because the file name contains a character that is not alphanumeric", index);
			}
		}

		return useFile(file);
	}

	@Override
	public FileVisitResult visitFileFailed(@NotNull final Path file, @NotNull final IOException exc)
	{
		warnings.warn(format("Ignoring file named '%1$s' because it could not be opened because of %2$s (in path %3$s)", fileName(file), exc.getMessage(), file));
		return CONTINUE;
	}

	@NotNull
	@Override
	public FileVisitResult postVisitDirectory(@NotNull final Path dir, @Nullable final IOException exc)
	{
		return CONTINUE;
	}

	@NonNls
	@NotNull
	private static String fileName(@NotNull final Path file)
	{
		return file.getFileName().toString();
	}

	private static boolean isCharacterNonUsAscii(final char character)
	{
		return character > 127;
	}

	private static boolean isCharacterHyphen(final char character)
	{
		return character == '-';
	}

	private static boolean isCharacterPeriod(final char character)
	{
		return character == '.';
	}

	private static boolean isCharacterAlphanumeric(final char character)
	{
		return !(isCharacterWithinRange('0', character, '9') || isCharacterWithinRange('A', character, 'Z') || isCharacterWithinRange('a', character, 'z'));
	}

	@SuppressWarnings("CharacterComparison")
	private static boolean isCharacterWithinRange(final char greaterThanOrEqualTo, final char character, final char lessThanOrEqualTo)
	{
		return character >= greaterThanOrEqualTo && character <= lessThanOrEqualTo;
	}

	@NotNull
	private FileVisitResult ignoreDirectory(@NotNull final Path directoryPath, @NonNls @NotNull final String folderName, @NonNls @NotNull final String because, final int index)
	{
		return ignoreDirectory(directoryPath, folderName, becauseWithIndex(because, index));
	}

	@NotNull
	private FileVisitResult ignoreDirectory(@NotNull final Path directoryPath, @NonNls @NotNull final String folderName, @NonNls @NotNull final String because)
	{
		warnings.warn(format("Ignoring folder named '%1$s' %2$s (in path %3$s)", folderName, because, directoryPath));
		return SKIP_SUBTREE;
	}

	@NotNull
	private FileVisitResult ignoreFile(@NotNull final Path filePath, @NonNls @NotNull final String fileName, @NonNls @NotNull final String because, final int index)
	{
		return ignoreFile(filePath, fileName, becauseWithIndex(because, index));
	}

	@NotNull
	private FileVisitResult ignoreFile(@NotNull final Path filePath, @NonNls @NotNull final String fileName, @NonNls @NotNull final String because)
	{
		warnings.warn(format("Ignoring file named '%1$s' %2$s (in path %3$s)", fileName, because, filePath));
		return CONTINUE;
	}

	@NotNull
	private FileVisitResult useFile(@NotNull final Path file)
	{
		javaSourceFileUser.use(file);
		return CONTINUE;
	}

	@NotNull
	@NonNls
	private static String becauseWithIndex(@NonNls @NotNull final String because, final int index)
	{
		return because + " at index " + index;
	}
}

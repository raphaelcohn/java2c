package com.compilerUser.javaSourceFiles;

import com.compilerUser.exceptions.FatalCompilationException;
import com.compilerUser.warnings.Warnings;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static com.compilerUser.text.EnglishFormatter.format;
import static java.nio.file.Files.walkFileTree;

@SuppressWarnings("ClassNamePrefixedWithPackageName")
public final class JavaSourceFilesFinder
{
	@NotNull
	private final Warnings warnings;

	public JavaSourceFilesFinder(@NotNull final Warnings warnings)
	{
		this.warnings = warnings;
	}

	@NotNull
	public Iterable<File> find(@NotNull final Path sourcePath) throws FatalCompilationException
	{
		final Collection<File> javaSourceFiles = new ArrayList<>(16);

		try
		{
			walkFileTree(sourcePath, new StatefulJavaSourceFileVisitor(warnings, new JavaSourceFileUser()
			{
				@Override
				public void use(@NotNull final Path javaSourceFilePath)
				{
					javaSourceFiles.add(javaSourceFilePath.toFile());
				}
			}));
		}
		catch (final IOException e)
		{
			throw new FatalCompilationException(format("Ignoring entire contents of source path %1$s because of '%2$s'", sourcePath, e.getMessage()), e);
		}

		return javaSourceFiles;
	}
}

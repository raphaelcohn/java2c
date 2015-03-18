package com.java2c.libraryHelpers.c;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class RegularFileDescriptor extends FileDescriptor
{
	// Uses open() or creat()
	public RegularFileDescriptor(@NotNull final Path name, @NotNull final c_number flags, @NotNull final mode_t mode)
	{
		super(value);
	}
}

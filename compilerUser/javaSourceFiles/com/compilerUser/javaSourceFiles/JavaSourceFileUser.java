package com.compilerUser.javaSourceFiles;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface JavaSourceFileUser
{
	void use(@NotNull final Path javaSourceFilePath);
}

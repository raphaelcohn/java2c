package com.java2c.intellij.commandLine.usingExecutors;

import org.jetbrains.annotations.NotNull;

public interface UsingExecutor<V>
{
	void use(@NotNull final V using);
}

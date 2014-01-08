package com.stormmq.java2c.transpiler.javaModules;

import org.jetbrains.annotations.NotNull;

public interface VargsDelegate<R, A>
{
	@SuppressWarnings("unchecked")
	@NotNull
	R execute(@NotNull final A... arguments);
}

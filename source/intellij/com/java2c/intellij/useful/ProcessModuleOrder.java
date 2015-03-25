package com.java2c.intellij.useful;

import com.intellij.openapi.roots.RootPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ProcessModuleOrder
{
	<R> void useModuleOrderEntriesInModuleDependencyOrder(@NotNull RootPolicy<R> rootPolicy, @Nullable final R initialValue);
}

package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.OrderEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface OrderEntryVisitor<R, V extends OrderEntry>
{
	@Nullable
	R visit(@NotNull final V orderEntry, @Nullable final R initialValue);
}

package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.ModuleOrderEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ModuleOrderEntryVisitor<R> extends OrderEntryVisitor<R, ModuleOrderEntry>
{
	@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
	@Override
	@Nullable
	R visit(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @NotNull final ModuleOrderEntry moduleOrderEntry, @Nullable final R initialValue);
}

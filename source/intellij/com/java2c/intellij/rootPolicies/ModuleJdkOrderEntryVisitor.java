package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.ModuleJdkOrderEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ModuleJdkOrderEntryVisitor<R> extends OrderEntryVisitor<R, ModuleJdkOrderEntry>
{
	@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
	@Override
	@Nullable
	R visit(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @NotNull final ModuleJdkOrderEntry moduleJdkOrderEntry, @Nullable final R initialValue);
}

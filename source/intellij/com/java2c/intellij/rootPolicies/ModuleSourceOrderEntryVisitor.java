package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.ModuleSourceOrderEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ModuleSourceOrderEntryVisitor<R> extends OrderEntryVisitor<R, ModuleSourceOrderEntry>
{
	@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
	@Override
	@Nullable
	R visit(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @NotNull final ModuleSourceOrderEntry moduleSourceOrderEntry, @Nullable final R initialValue);
}

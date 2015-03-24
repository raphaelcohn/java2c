package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.InheritedJdkOrderEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ProjectJdkOrderEntryVisitor<R> extends OrderEntryVisitor<R, InheritedJdkOrderEntry>
{
	@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
	@Override
	@Nullable
	R visit(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @NotNull final InheritedJdkOrderEntry projectJdkOrderEntry, @Nullable final R initialValue);
}

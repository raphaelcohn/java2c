package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.LibraryOrderEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LibraryOrderEntryVisitor<R> extends OrderEntryVisitor<R, LibraryOrderEntry>
{
	@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
	@Override
	@Nullable
	R visit(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @NotNull final LibraryOrderEntry libraryOrderEntry, @Nullable final R initialValue);
}

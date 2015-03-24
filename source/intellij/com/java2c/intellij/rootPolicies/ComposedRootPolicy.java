package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ComposedRootPolicy<R> extends AbstractRootPolicy<R>
{
	@NotNull
	private final OrderEntryVisitor<R, ModuleSourceOrderEntry> moduleSourceOrderEntryVisitor;

	@NotNull
	private final OrderEntryVisitor<R, LibraryOrderEntry> libraryOrderEntryVisitor;

	@NotNull
	private final OrderEntryVisitor<R, ModuleOrderEntry> moduleOrderEntryVisitor;

	@NotNull
	private final OrderEntryVisitor<R, ModuleJdkOrderEntry> moduleJdkOrderEntryVisitor;

	@NotNull
	private final OrderEntryVisitor<R, InheritedJdkOrderEntry> projectJdkOrderEntryVisitor;

	public ComposedRootPolicy(@NotNull final OrderEntryVisitor<R, ModuleSourceOrderEntry> moduleSourceOrderEntryVisitor, @NotNull final OrderEntryVisitor<R, LibraryOrderEntry> libraryOrderEntryVisitor, @NotNull final OrderEntryVisitor<R, ModuleOrderEntry> moduleOrderEntryVisitor, @NotNull final OrderEntryVisitor<R, ModuleJdkOrderEntry> moduleJdkOrderEntryVisitor, @NotNull final OrderEntryVisitor<R, InheritedJdkOrderEntry> projectJdkOrderEntryVisitor)
	{
		this.moduleSourceOrderEntryVisitor = moduleSourceOrderEntryVisitor;
		this.libraryOrderEntryVisitor = libraryOrderEntryVisitor;
		this.moduleOrderEntryVisitor = moduleOrderEntryVisitor;
		this.moduleJdkOrderEntryVisitor = moduleJdkOrderEntryVisitor;
		this.projectJdkOrderEntryVisitor = projectJdkOrderEntryVisitor;
	}

	@Nullable
	@Override
	public R visitModuleSourceOrderEntry(@NotNull final ModuleSourceOrderEntry moduleSourceOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue)
	{
		return moduleSourceOrderEntryVisitor.visit(moduleSourceOrderEntry, initialValue);
	}

	@Nullable
	@Override
	public R visitLibraryOrderEntry(@NotNull final LibraryOrderEntry libraryOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue)
	{
		return libraryOrderEntryVisitor.visit(libraryOrderEntry, initialValue);
	}

	@Nullable
	@Override
	public R visitModuleOrderEntry(@NotNull final ModuleOrderEntry moduleOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue)
	{
		return moduleOrderEntryVisitor.visit(moduleOrderEntry, initialValue);
	}

	@Nullable
	@Override
	public R visitModuleJdkOrderEntry(@NotNull final ModuleJdkOrderEntry jdkOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue)
	{
		return moduleJdkOrderEntryVisitor.visit(jdkOrderEntry, initialValue);
	}

	@Nullable
	@Override
	public R visitInheritedJdkOrderEntry(@NotNull final InheritedJdkOrderEntry inheritedJdkOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue)
	{
		return projectJdkOrderEntryVisitor.visit(inheritedJdkOrderEntry, initialValue);
	}
}

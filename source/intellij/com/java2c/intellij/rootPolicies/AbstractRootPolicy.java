package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("AbstractClassExtendsConcreteClass")
public abstract class AbstractRootPolicy<R> extends RootPolicy<R>
{
	protected AbstractRootPolicy()
	{
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	@Nullable
	public final R visitOrderEntry(@NotNull final OrderEntry orderEntry, @Nullable final R value)
	{
		throw new UnsupportedOperationException("visitOrderEntry should not be visited");
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	@Nullable
	public final R visitJdkOrderEntry(@NotNull final JdkOrderEntry jdkOrderEntry, @Nullable final R value)
	{
		throw new UnsupportedOperationException("visitJdkOrderEntry should not be visited");
	}

	@SuppressWarnings("AbstractMethodOverridesConcreteMethod")
	@Override
	@Nullable
	public abstract R visitModuleSourceOrderEntry(@NotNull final ModuleSourceOrderEntry moduleSourceOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue);

	@SuppressWarnings("AbstractMethodOverridesConcreteMethod")
	@Override
	@Nullable
	public abstract R visitLibraryOrderEntry(@NotNull final LibraryOrderEntry libraryOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue);

	@SuppressWarnings("AbstractMethodOverridesConcreteMethod")
	@Override
	@Nullable
	public abstract R visitModuleOrderEntry(@NotNull final ModuleOrderEntry moduleOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue);

	@SuppressWarnings("AbstractMethodOverridesConcreteMethod")
	@Override
	@Nullable
	public abstract R visitModuleJdkOrderEntry(@NotNull final ModuleJdkOrderEntry jdkOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue);

	@SuppressWarnings("AbstractMethodOverridesConcreteMethod")
	@Override
	@Nullable
	public abstract R visitInheritedJdkOrderEntry(@NotNull final InheritedJdkOrderEntry inheritedJdkOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final R initialValue);

}

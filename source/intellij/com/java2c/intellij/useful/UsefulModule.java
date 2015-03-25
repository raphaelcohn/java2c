package com.java2c.intellij.useful;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.RootPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class UsefulModule implements ProcessModuleOrder
{
	@SuppressWarnings("PublicField")
	@NotNull
	public final Module module;

	@SuppressWarnings("PublicField")
	@NotNull
	public final ModuleRootManager moduleRootManager;

	// module may be ModuleEx
	public UsefulModule(@NotNull final Module module)
	{
		this.module = module;
		moduleRootManager = moduleRootManager();
	}

	@Override
	public <R> void useModuleOrderEntriesInModuleDependencyOrder(@NotNull final RootPolicy<R> rootPolicy, @Nullable final R initialValue)
	{
		@Nullable final R result = moduleRootManager.processOrder(rootPolicy, initialValue);
		assert result == null;
	}

	@NotNull
	private ModuleRootManager moduleRootManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
		assert moduleRootManager != null;
		return moduleRootManager;
	}
}

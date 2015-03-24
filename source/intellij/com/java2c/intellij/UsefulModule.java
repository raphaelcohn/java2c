package com.java2c.intellij;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.java2c.intellij.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import com.java2c.intellij.rootPolicies.AbstractRootPolicy;
import com.java2c.intellij.rootPolicies.OrderEntryValidatingRootPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class UsefulModule implements ProcessModuleOrder
{
	@NotNull
	private static final OrderEntryValidatingRootPolicy OrderEntryValidator = new OrderEntryValidatingRootPolicy();

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
	public void validateModuleOrderEntriesInModuleDependencyOrder(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder)
	{
		moduleRootManager.processOrder(OrderEntryValidator, projectValidationMessagesRecorder);
	}

	@Override
	public void useModuleOrderEntriesInModuleDependencyOrder(@NotNull final AbstractRootPolicy<Object> abstractRootPolicy)
	{
		@Nullable final Object result = moduleRootManager.processOrder(abstractRootPolicy, null);
	}

	@NotNull
	private ModuleRootManager moduleRootManager()
	{
		@SuppressWarnings("LocalVariableHidesMemberVariable") final ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
		assert moduleRootManager != null;
		return moduleRootManager;
	}
}

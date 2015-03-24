package com.java2c.intellij;

import com.java2c.intellij.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import com.java2c.intellij.rootPolicies.AbstractRootPolicy;
import org.jetbrains.annotations.NotNull;

public interface ProcessModuleOrder
{
	void validateModuleOrderEntriesInModuleDependencyOrder(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder);

	void useModuleOrderEntriesInModuleDependencyOrder(@NotNull AbstractRootPolicy<Object> abstractRootPolicy);
}

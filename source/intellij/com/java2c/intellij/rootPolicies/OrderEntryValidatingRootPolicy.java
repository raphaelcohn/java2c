package com.java2c.intellij.rootPolicies;

import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.RootPolicy;
import com.java2c.intellij.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import org.jetbrains.annotations.NotNull;

public final class OrderEntryValidatingRootPolicy extends RootPolicy<ProjectValidationMessagesRecorder>
{
	@SuppressWarnings({"unchecked", "TypeMayBeWeakened"})
	@NotNull
	private static final ValidatingOrderEntryVisitor<OrderEntry> Visitor = new ValidatingOrderEntryVisitor();

	@SuppressWarnings({"RefusedBequest", "Contract"})
	@NotNull
	@Override
	public ProjectValidationMessagesRecorder visitOrderEntry(@NotNull final OrderEntry orderEntry, @NotNull final ProjectValidationMessagesRecorder value)
	{
		return Visitor.visit(orderEntry, value);
	}
}

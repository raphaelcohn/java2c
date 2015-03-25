package com.java2c.intellij.builder.validation;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.RootPolicy;
import com.intellij.openapi.vfs.VirtualFile;
import com.java2c.intellij.builder.validation.projectValidationMessagesRecorders.ProjectValidationMessagesRecorder;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.compiler.CompilerMessageCategory.ERROR;
import static com.intellij.openapi.roots.OrderRootType.getAllTypes;
import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class OrderEntryValidatingRootPolicy extends RootPolicy<ProjectValidationMessagesRecorder>
{
	@NotNull
	public static final RootPolicy<ProjectValidationMessagesRecorder> OrderEntryValidatingRootPolicyInstance = new OrderEntryValidatingRootPolicy();

	private OrderEntryValidatingRootPolicy()
	{
	}

	@SuppressWarnings({"RefusedBequest", "Contract"})
	@NotNull
	@Override
	public ProjectValidationMessagesRecorder visitOrderEntry(@NotNull final OrderEntry orderEntry, @NotNull final ProjectValidationMessagesRecorder value)
	{
		validate(value, orderEntry);
		return value;
	}

	private static void validate(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder, @NotNull final OrderEntry orderEntry)
	{
		if (!orderEntry.isValid())
		{
			assert ERROR != null;
			projectValidationMessagesRecorder.record(project(orderEntry), ERROR, getOrderEntryDescription(orderEntry));
			return;
		}

		final OrderRootType[] allTypes = getAllTypes();
		assert allTypes != null;

		for (final OrderRootType orderRootType : allTypes)
		{
			validateRoots(projectValidationMessagesRecorder, orderEntry, orderRootType);
		}
	}

	private static void validateRoots(@NotNull final ProjectValidationMessagesRecorder projectValidationMessagesRecorder, @NotNull final OrderEntry orderEntry, @NotNull final OrderRootType orderRootType)
	{
		for (final VirtualFile file : orderEntry.getFiles(orderRootType))
		{
			if (!file.isValid())
			{
				assert ERROR != null;
				final String format = format(ENGLISH, "Root '%1$s' of type '%2$s' in %3$s", file.getName(), orderRootType.name(), getOrderEntryDescription(orderEntry)); //NON-NLS
				assert format != null;
				projectValidationMessagesRecorder.record(project(orderEntry), ERROR, format);
			}
		}
	}

	@NotNull
	private static Project project(@NotNull final OrderEntry orderEntry)
	{
		return orderEntry.getOwnerModule().getProject();
	}

	@NotNull
	private static String getOrderEntryDescription(@NotNull final OrderEntry orderEntry)
	{
		final Module ownerModule = orderEntry.getOwnerModule();
		final String format = format(ENGLISH, "OrderEntry '%1$s' in module '%2$s' is invalid", orderEntry.getPresentableName(), ownerModule.getName()); //NON-NLS
		assert format != null;
		return format;
	}
}

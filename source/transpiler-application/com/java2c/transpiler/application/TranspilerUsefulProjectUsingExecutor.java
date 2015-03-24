package com.java2c.transpiler.application;

import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.vfs.VirtualFile;
import com.java2c.intellij.UsefulProject;
import com.java2c.intellij.commandLine.usingExecutors.UsingExecutor;
import com.java2c.intellij.projectValidationMessagesRecorders.ErrorTrackingProjectValidationMessagesRecords;
import com.java2c.intellij.rootPolicies.AbstractRootPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.openapi.roots.OrderRootType.CLASSES;
import static com.java2c.intellij.projectValidationMessagesRecorders.PrintStreamProjectValidationMessagesRecorder.StandardErrorPrintStreamProjectValidationMessagesRecorder;

public final class TranspilerUsefulProjectUsingExecutor implements UsingExecutor<UsefulProject>
{
	@Override
	public void use(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @NotNull final UsefulProject usefulProject)
	{
		final ErrorTrackingProjectValidationMessagesRecords projectValidationMessagesRecorder = new ErrorTrackingProjectValidationMessagesRecords(StandardErrorPrintStreamProjectValidationMessagesRecorder);

		usefulProject.validateArtifacts(projectValidationMessagesRecorder);
		usefulProject.validateModuleOrderEntriesInModuleDependencyOrder(projectValidationMessagesRecorder);
		usefulProject.validateCanRunInspections(projectValidationMessagesRecorder);

		if (projectValidationMessagesRecorder.hasErrors())
		{
			System.exit(3);
			return;
		}

		usefulProject.validateInspections(projectValidationMessagesRecorder);
		if (projectValidationMessagesRecorder.hasErrors())
		{
			System.exit(3);
			return;
		}


		usefulProject.rebuild(projectValidationMessagesRecorder);
		if (projectValidationMessagesRecorder.hasErrors())
		{
			System.exit(3);
			return;
		}

		/*

			eg we could put include files into

				java2c/
					<moduleName>/
						com/
							java2c/
								samples/
									AnInterface.c

		 */

		usefulProject.useModuleOrderEntriesInModuleDependencyOrder(new AbstractRootPolicy<Object>()
		{
			@Nullable
			@Override
			public Object visitModuleSourceOrderEntry(@NotNull final ModuleSourceOrderEntry moduleSourceOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final Object initialValue)
			{
				return null;
			}

			@Nullable
			@Override
			public Object visitLibraryOrderEntry(@NotNull final LibraryOrderEntry libraryOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final Object initialValue)
			{
				libraryOrderEntry.isExported();

				libraryOrderEntry.isSynthetic();

				libraryOrderEntry.isValid();

				final VirtualFile[] classPath = libraryOrderEntry.getRootFiles(CLASSES);

				// Also LibraryEx
				final Library library = libraryOrderEntry.getLibrary();

				// project or application or "module" library
				libraryOrderEntry.isModuleLevel();
				final String libraryLevel = libraryOrderEntry.getLibraryLevel();

				return null;
			}

			@Nullable
			@Override
			public Object visitModuleOrderEntry(@NotNull final ModuleOrderEntry moduleOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final Object initialValue)
			{
				// does this get added to children dependencies class paths?
				moduleOrderEntry.isExported();

				// is this entry for production?
				final DependencyScope scope = moduleOrderEntry.getScope();
				return null;
			}

			@Nullable
			@Override
			public Object visitModuleJdkOrderEntry(@NotNull final ModuleJdkOrderEntry jdkOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final Object initialValue)
			{
				return null;
			}

			@Nullable
			@Override
			public Object visitInheritedJdkOrderEntry(@NotNull final InheritedJdkOrderEntry inheritedJdkOrderEntry, @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @Nullable final Object initialValue)
			{
				return null;
			}
		});
	}
}

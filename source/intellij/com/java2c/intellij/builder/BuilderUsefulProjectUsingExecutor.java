package com.java2c.intellij.builder;

import com.java2c.intellij.builder.rebuilding.ProjectOfflineRebuilder;
import com.java2c.intellij.builder.validation.ProjectValidator;
import com.java2c.intellij.useful.UsefulProject;
import com.java2c.intellij.commandLine.usingExecutors.UsingExecutor;
import com.java2c.intellij.builder.validation.projectValidationMessagesRecorders.ErrorTrackingProjectValidationMessagesRecords;
import org.jetbrains.annotations.NotNull;

import static com.java2c.intellij.builder.validation.projectValidationMessagesRecorders.PrintStreamProjectValidationMessagesRecorder.StandardErrorPrintStreamProjectValidationMessagesRecorder;
import static java.lang.System.exit;

public final class BuilderUsefulProjectUsingExecutor implements UsingExecutor<UsefulProject>
{
	@Override
	public void use(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") @NotNull final UsefulProject usefulProject)
	{
		final ErrorTrackingProjectValidationMessagesRecords projectValidationMessagesRecorder = new ErrorTrackingProjectValidationMessagesRecords(StandardErrorPrintStreamProjectValidationMessagesRecorder);

		final ProjectValidator projectValidator = new ProjectValidator(usefulProject);
		projectValidator.validateArtifacts(projectValidationMessagesRecorder);
		projectValidator.validateModuleOrderEntriesInModuleDependencyOrder(projectValidationMessagesRecorder);
		projectValidator.validateCanRunInspections(projectValidationMessagesRecorder);

		if (projectValidationMessagesRecorder.hasErrors())
		{
			exit(3);
			return;
		}

		projectValidator.validateInspections(projectValidationMessagesRecorder);
		if (projectValidationMessagesRecorder.hasErrors())
		{
			exit(3);
			return;
		}

		new ProjectOfflineRebuilder(usefulProject).offlineRebuild(projectValidationMessagesRecorder);
		if (projectValidationMessagesRecorder.hasErrors())
		{
			exit(3);
			return;
		}
	}

}

package com.java2c.intellij.commandLine.commandLineApplicationStarterExs;

import com.intellij.openapi.project.Project;
import com.java2c.intellij.UsefulProject;
import com.java2c.intellij.commandLine.usingExecutors.UsingExecutor;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractUsefulProjectUsingCommandLineApplicationStarterEx extends AbstractProjectUsingCommandLineApplicationStarterEx
{
	protected AbstractUsefulProjectUsingCommandLineApplicationStarterEx(@NotNull final UsingExecutor<UsefulProject> usefulProjectUsingExecutor)
	{
		super(new UsingExecutor<Project>()
		{
			@Override
			public void use(@NotNull final Project using)
			{
				usefulProjectUsingExecutor.use(new UsefulProject(using));
			}
		});
	}
}

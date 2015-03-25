package com.java2c.intellij.builder;

import com.java2c.intellij.commandLine.commandLineApplicationStarterExs.AbstractUsefulProjectUsingCommandLineApplicationStarterEx;

@SuppressWarnings("UnusedDeclaration")
public final class BuilderCommandLineApplicationStarterEx extends AbstractUsefulProjectUsingCommandLineApplicationStarterEx
{
	public BuilderCommandLineApplicationStarterEx()
	{
		super(new BuilderUsefulProjectUsingExecutor());
	}
}

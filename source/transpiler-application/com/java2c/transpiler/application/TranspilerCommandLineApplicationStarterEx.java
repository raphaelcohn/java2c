package com.java2c.transpiler.application;

import com.java2c.intellij.commandLine.commandLineApplicationStarterExs.AbstractUsefulProjectUsingCommandLineApplicationStarterEx;

@SuppressWarnings("UnusedDeclaration")
public final class TranspilerCommandLineApplicationStarterEx extends AbstractUsefulProjectUsingCommandLineApplicationStarterEx
{
	public TranspilerCommandLineApplicationStarterEx()
	{
		super(new TranspilerUsefulProjectUsingExecutor());
	}
}

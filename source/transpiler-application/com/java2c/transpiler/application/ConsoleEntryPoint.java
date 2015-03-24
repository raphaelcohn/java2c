package com.java2c.transpiler.application;

import com.java2c.intellij.commandLine.IntelliJCommandLineInvoker;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UtilityClass")
public final class ConsoleEntryPoint
{
	// Has to be done this way to prevent the class loading on a different ClassLoader before IntelliJ initialised
	@NotNull
	@NonNls
	private static final String abstractCommandLineApplicationStarterExClassName = "com.java2c.transpiler.application.TranspilerCommandLineApplicationStarterEx";

	public static void main(@NotNull final String... args)
	{
		final String homePath = "/Applications/IntelliJ IDEA 14 CE.app/Contents";
		new IntelliJCommandLineInvoker(homePath).invoke(abstractCommandLineApplicationStarterExClassName, "/Users/raph/Documents/GitHub/raphaelcohn/java2c/source/java2c.ipr");
	}

	private ConsoleEntryPoint()
	{
	}

}

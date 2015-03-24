package com.java2c.intellij.commandLine;

import com.intellij.ide.Bootstrap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import static com.intellij.idea.Main.setFlags;
import static com.intellij.openapi.application.PathManager.PROPERTY_HOME_PATH;
import static com.java2c.intellij.commandLine.commandLineApplicationStarterExs.AbstractCommandLineApplicationStarterEx.UnexpectedErrorExitCode;
import static java.lang.Boolean.TRUE;
import static java.lang.System.*;

public final class IntelliJCommandLineInvoker
{
	@NotNull
	@NonNls
	private static final String JavaAwtHeadless = "java.awt.headless";

	@NotNull
	private static final String[] Empty = {};

	@NotNull
	@NonNls
	private static final String MainImplReplacement = "com.intellij.idea.MainImplReplacement";

	@NotNull
	private final String homePath;

	public IntelliJCommandLineInvoker(@NotNull final String homePath)
	{
		this.homePath = homePath;
	}

	public void invoke(@NotNull final String abstractCommandLineApplicationStarterExClassName, @NotNull final String... commandLineArguments)
	{
		forceIntelliJHome(homePath);
		forceIntelliJCommunityEditionToBeHeadlessAndInCommandLineMode();
		invokeIntelliJWithOurApplication(abstractCommandLineApplicationStarterExClassName, commandLineArguments);
	}

	@SuppressWarnings("AccessOfSystemProperties")
	private static void forceIntelliJHome(@NotNull final String homePath)
	{
		setProperty(PROPERTY_HOME_PATH, homePath);
	}

	@SuppressWarnings("AccessOfSystemProperties")
	private static void forceIntelliJCommunityEditionToBeHeadlessAndInCommandLineMode()
	{
		setProperty(JavaAwtHeadless, TRUE.toString());
		setFlags(Empty);
	}

	@SuppressWarnings({"StringConcatenationMissingWhitespace", "UseOfSystemOutOrSystemErr", "CallToSystemExit"})
	private static void invokeIntelliJWithOurApplication(@NotNull final String abstractCommandLineApplicationStarterExClassName, @NotNull final String... commandLineArguments)
	{
		final int length = commandLineArguments.length;
		final String[] intelliJCommandLineArguments = new String[length + 1];
		intelliJCommandLineArguments[0] = abstractCommandLineApplicationStarterExClassName;
		arraycopy(commandLineArguments, 0, intelliJCommandLineArguments, 1, length);

		try
		{
			Bootstrap.main(intelliJCommandLineArguments, com.intellij.idea.MainImplReplacement.class.getCanonicalName(), "start");
		}
		catch (final Exception e)
		{
			e.printStackTrace(err);
			exit(UnexpectedErrorExitCode);
		}
	}
}

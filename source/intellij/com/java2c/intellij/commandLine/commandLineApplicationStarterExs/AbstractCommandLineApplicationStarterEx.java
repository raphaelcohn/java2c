/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.java2c.intellij.commandLine.commandLineApplicationStarterExs;

import com.intellij.idea.IdeaApplication;
import com.intellij.openapi.application.ApplicationStarterEx;
import com.intellij.openapi.application.ex.ApplicationEx;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.intellij.idea.IdeaApplication.IDEA_IS_UNIT_TEST;
import static com.intellij.openapi.application.ex.ApplicationManagerEx.getApplicationEx;
import static java.lang.Boolean.TRUE;
import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.setProperty;
import static java.util.Arrays.asList;

public abstract class AbstractCommandLineApplicationStarterEx extends ApplicationStarterEx
{
	public static final int SuccessExitCode = 0;
	public static final int ErrorExitCode = 1;
	public static final int UnexpectedErrorExitCode = 2;

	@SuppressWarnings({"AccessOfSystemProperties", "ConstantConditions"})
	protected AbstractCommandLineApplicationStarterEx()
	{
		// Causes ApplicationManager.getInstance().isUnitTestMode() to be true
		setProperty(IDEA_IS_UNIT_TEST, TRUE.toString());
	}

	@Override
	@NotNull
	public final String toString()
	{
		return getCommandName();
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	public final boolean canProcessExternalCommandLine()
	{
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public final void processExternalCommandLine(@NotNull final String[] args)
	{
		super.processExternalCommandLine(args);
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	public final void processExternalCommandLine(@NotNull final String[] args, @Nullable final String currentDirectory)
	{
		throw new IllegalStateException("Why?");
	}

	@Override
	public final boolean isHeadless()
	{
		return true;
	}

	@Override
	@NotNull
	@NonNls
	public final String getCommandName()
	{
		final Class<? extends AbstractCommandLineApplicationStarterEx> aClass = getClass();
		assert aClass != null;

		@Nullable final String canonicalName = aClass.getCanonicalName();
		if (canonicalName == null)
		{
			throw new IllegalStateException("An ApplicationStarter can not be a local or anonymous class");
		}
		return canonicalName;
	}

	@Override
	public final void premain(@NotNull final String... args)
	{
		// Process arguments here, exit(1) if invalid
	}

	@SuppressWarnings({"ConfusingMainMethod", "CallToSystemExit", "UseOfSystemOutOrSystemErr"})
	@Override
	public final void main(@NotNull final String... args)
	{
		final String ourCommandName = args[0];
		//noinspection CallToSimpleGetterFromWithinClass
		assert getCommandName().equals(ourCommandName);

		final List<String> commandLineArgumentsExcludingCommandName = asList(args).subList(1, args.length - 1);
		final int exitCode;
		try
		{
			// Not convinced we need to use runReadAction, but it can't hurt
			final int[] wrappedExitCode = new int[1];
			final ApplicationEx application = application();
			application.runReadAction(new Runnable()
			{
				@Override
				public void run()
				{
					wrappedExitCode[0] = execute(commandLineArgumentsExcludingCommandName);
				}
			});
			application.exit();
			// Causes System.exit(0) - not what we want
			// application.exit(true, true);
			exitCode = wrappedExitCode[0];
		}
		catch (final Throwable e)
		{
			e.printStackTrace(err);
			exit(UnexpectedErrorExitCode);
			return;
		}
		exit(exitCode);
	}

	protected abstract int execute(@NotNull final List<String> commandLineArgumentsExcludingCommandName);

	@NotNull
	private static ApplicationEx application()
	{
		final ApplicationEx application = getApplicationEx();
		assert application != null;
		return application;
	}
}

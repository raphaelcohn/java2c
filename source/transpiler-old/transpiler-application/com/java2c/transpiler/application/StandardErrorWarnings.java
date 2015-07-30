/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.application;

import com.compilerUser.warnings.Warnings;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

import static com.compilerUser.text.EnglishFormatter.FormatterLocale;
import static com.compilerUser.text.EnglishFormatter.format;
import static java.lang.System.err;

public final class StandardErrorWarnings implements Warnings
{
	@NotNull
	private static final Locale Locale = FormatterLocale;

	@Override
	public void fatal(@NotNull final Exception cause)
	{
		@Nullable final String message = cause.getMessage();
		message("FATAL", message == null ? "(unknown)" : message);
	}

	@Override
	public void warn(@NotNull @NonNls final String warning)
	{
		message("WARN", warning);
	}

	@Override
	public void information(@NotNull @NonNls final String information)
	{
		message("INFO", information);
	}

	@NotNull
	@Override
	public Locale locale()
	{
		return Locale;
	}

	@SuppressWarnings({"UseOfSystemOutOrSystemErr", "SynchronizationOnStaticField"})
	private static void message(@NonNls @NotNull final String fatality, @NonNls @NotNull final String warning)
	{
		synchronized (err)
		{
			err.println(format("%1$s: %2$s", fatality, warning));
		}
	}
}

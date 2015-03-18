package com.java2c.transpiler.warnings;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static com.java2c.utility.EnglishFormatter.FormatterLocale;
import static com.java2c.utility.EnglishFormatter.format;
import static java.lang.System.err;

public final class StandardErrorWarnings implements Warnings
{
	@NotNull
	private static final Locale Locale = FormatterLocale;

	@Override
	public void fatal(@NotNull final Exception cause)
	{
		message("FATAL", cause.getMessage());
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
	private static void message(@NonNls @NotNull final String fatality, @NotNull final String warning)
	{
		synchronized (err)
		{
			err.println(format("%1$s: %2$s", fatality, warning));
		}
	}
}

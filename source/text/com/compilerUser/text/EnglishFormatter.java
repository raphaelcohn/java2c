package com.compilerUser.text;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

@SuppressWarnings("UtilityClass")
public final class EnglishFormatter
{
	@NotNull
	public static final Locale FormatterLocale = ENGLISH;

	@NotNull
	@NonNls
	public static String format(@NotNull @NonNls final String format, @NotNull @NonNls final Object... args)
	{
		return String.format(FormatterLocale, format, args);
	}

	private EnglishFormatter()
	{
	}
}

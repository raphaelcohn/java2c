package com.compilerUser.text;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;

import static java.nio.charset.Charset.forName;

public final class CharsetHelper
{
	@NotNull public static final Charset Utf8 = forName("UTF-8");

	private CharsetHelper()
	{
	}
}

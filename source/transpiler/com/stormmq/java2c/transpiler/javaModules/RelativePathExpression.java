package com.stormmq.java2c.transpiler.javaModules;

import org.jetbrains.annotations.NotNull;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.nio.file.Paths.get;
import static java.util.Locale.ENGLISH;
import static java.util.regex.Pattern.compile;

public final class RelativePathExpression
{
	@NotNull private static final Pattern Regex = compile("%.?");
	@NotNull private static final Map<String, VargsDelegate<String, String>> Replacements = new HashMap<String, VargsDelegate<String, String>>(2)
	{{
		put("%%", new VargsDelegate<String, String>()
		{
			@NotNull
			@Override
			public String execute(@NotNull final String... arguments)
			{
				return "%";
			}
		});

		put("%m", new VargsDelegate<String, String>()
		{
			@NotNull
			@Override
			public String execute(@NotNull final String... arguments)
			{
				return arguments[0];
			}
		});
	}};

	@NotNull private final String relativePathExpression;

	// Replace %m with moduleName
	public RelativePathExpression(@NotNull final String relativePathExpression) throws IllegalRelativePathExpressionException
	{
		this.relativePathExpression = relativePathExpression;
		final Matcher matcher = match();
		while(matcher.find())
		{
			final int start = matcher.start();
			final int end = matcher.end();
			final String matched = relativePathExpression.substring(start, end);
			if (!Replacements.containsKey(matched))
			{
				throw new IllegalRelativePathExpressionException(format(ENGLISH, "The token '%1$s' between indices %2$s and %3$s (%4$s) is invalid", matched, start, end, matched));
			}
		}
	}

	@NotNull
	public final Path evaluateToPath(@NotNull final ModuleName moduleName) throws IllegalRelativePathException
	{
		final StringBuffer buffer = new StringBuffer(4096);
		final Matcher matcher = match();
		while(matcher.find())
		{
			final String matched = relativePathExpression.substring(matcher.start(), matcher.end());
			final String replacement = Replacements.get(matched).execute(moduleName.asString());
			matcher.appendReplacement(buffer, replacement);
		}
		matcher.appendTail(buffer);
		final String stringPath = buffer.toString();
		final Path relativePath;
		try
		{
			relativePath = get(stringPath);
		}
		catch (InvalidPathException e)
		{
			throw new IllegalRelativePathException(stringPath, moduleName, relativePathExpression, e);
		}

		if (relativePath.isAbsolute())
		{
			throw new IllegalRelativePathException(stringPath, moduleName, relativePathExpression);
		}
		return relativePath;
	}

	private Matcher match()
	{
		return Regex.matcher(relativePathExpression);
	}
}

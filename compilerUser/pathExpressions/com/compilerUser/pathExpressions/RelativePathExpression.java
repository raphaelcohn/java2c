package com.compilerUser.pathExpressions;

import com.compilerUser.moduleName.ModuleName;
import org.jetbrains.annotations.NonNls;
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
	@NotNull
	private static final Pattern Regex = compile("%.?");

	@NotNull
	private static final Map<String, StringVariableArgumentsDelegate> Replacements = new HashMap<String, StringVariableArgumentsDelegate>(2)
	{{
		put("%%", new StringVariableArgumentsDelegate()
		{
			@SuppressWarnings("OverloadedVarargsMethod")
			@NotNull
			@Override
			public String execute(@NotNull final ModuleName argument)
			{
				return "%";
			}
		});

		put("%m", new StringVariableArgumentsDelegate()
		{
			@SuppressWarnings("OverloadedVarargsMethod")
			@NotNull
			@Override
			public String execute(@NotNull final ModuleName argument)
			{
				return argument.asString();
			}
		});
	}};

	@NotNull private final String relativePathExpression;

	// Replace %m with moduleName
	public RelativePathExpression(@NonNls @NotNull final String relativePathExpression) throws IllegalRelativePathExpressionException
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
				throw new IllegalRelativePathExpressionException(format(ENGLISH, "The token '%1$s' between indices %2$s and %3$s (%4$s) is invalid", matched, start, end, matched)); //NON-NLS
			}
		}
	}

	@NotNull
	public Path evaluateToPath(@NotNull final ModuleName moduleName) throws IllegalRelativePathException
	{
		final StringBuffer buffer = new StringBuffer(4096);
		final Matcher matcher = match();
		while(matcher.find())
		{
			final String matched = relativePathExpression.substring(matcher.start(), matcher.end());
			final StringVariableArgumentsDelegate stringVariableArgumentsDelegate = Replacements.get(matched);
			assert stringVariableArgumentsDelegate != null;
			final String replacement = stringVariableArgumentsDelegate.execute(moduleName);
			matcher.appendReplacement(buffer, replacement);
		}
		matcher.appendTail(buffer);
		final String stringPath = buffer.toString();
		final Path relativePath;
		try
		{
			relativePath = get(stringPath);
		}
		catch (final InvalidPathException e)
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

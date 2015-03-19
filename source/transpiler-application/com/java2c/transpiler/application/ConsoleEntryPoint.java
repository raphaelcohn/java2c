package com.java2c.transpiler.application;

import static com.java2c.utility.EnglishFormatter.format;
import com.java2c.javaCompiler.*;
import com.java2c.javaCompiler.pathExpressions.IllegalRelativePathExpressionException;
import com.java2c.javaCompiler.pathExpressions.RelativePathExpression;
import com.java2c.javaCompiler.pathExpressions.RootPathAndExpression;
import com.java2c.utility.ImpossibleStateException;
import com.java2c.transpiler.warnings.StandardErrorWarnings;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import static java.lang.System.*;
import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;

@SuppressWarnings("UtilityClass")
public final class ConsoleEntryPoint
{
	@SuppressWarnings("ConstantNamingConvention")
	@NotNull
	private static final String help = "help";

	@SuppressWarnings("ConstantNamingConvention")
	@NotNull
	private static final String modules_root = "modules-root";

	@SuppressWarnings("ConstantNamingConvention")
	@NotNull
	private static final String modules_relative_path_expression = "modules-relative-path-expression";

	@SuppressWarnings("ConstantNamingConvention")
	@NotNull
	private static final String source_output_root = "source-output-root";

	@SuppressWarnings("ConstantNamingConvention")
	@NotNull
	private static final String source_output_relative_path_expression = "source-output-relative-path-expression";

	@NonNls
	@NotNull
	private static final OptionParser CommandLineArgumentsParser = new OptionParser();

	@SuppressWarnings("HardcodedFileSeparator") @NotNull @NonNls private static final String examplePath = "eg /Users/raph/Documents/GitHub/raphaelcohn/java2c";

	static
	{
		CommandLineArgumentsParser.posixlyCorrect(true);

		CommandLineArgumentsParser.accepts(help, "show help").forHelp();

		CommandLineArgumentsParser.accepts(modules_root, "modules root path").withRequiredArg().describedAs(examplePath).ofType(String.class);

		//noinspection HardcodedFileSeparator
		CommandLineArgumentsParser.accepts(modules_relative_path_expression, "modules relative path expression").withRequiredArg().describedAs("eg source/%m").ofType(String.class).defaultsTo("source/%m"); //NON-NLS

		CommandLineArgumentsParser.accepts(source_output_root, "c output root path").withRequiredArg().describedAs(examplePath).ofType(String.class);

		//noinspection HardcodedFileSeparator
		CommandLineArgumentsParser.accepts(source_output_relative_path_expression, "c output relative path expression").withRequiredArg().describedAs("eg output/%m").ofType(String.class).defaultsTo("output/%m"); //NON-NLS

		CommandLineArgumentsParser.nonOptions("module names").ofType(ModuleName.class);
	}

	private static final int ExitCodeHelp = 0;
	private static final int ExitCodeError = 1;

	public static void main(@NotNull final String... commandLineArguments)
	{
		@NotNull final OptionSet arguments;
		try
		{
			arguments = CommandLineArgumentsParser.parse(commandLineArguments);
		}
		catch (final OptionException e)
		{
			printExceptionAndHelpThenExit(e);
			return;
		}

		if (arguments.has(help))
		{
			printHelpThenExit(ExitCodeHelp);
			return;
		}

		for(final String argument : requiredArguments(modules_root))
		{
			if (!arguments.has(argument))
			{
				printErrorMessageAndHelpThenExit("Missing required argument %1$s", argument);
				return;
			}
		}

		final Path modulesRootPath = getModulesRootPath(arguments);
		final RelativePathExpression modulesRelativePathExpression = getModulesRelativePathExpression(arguments);
		final Path sourceOutputRootPath = getSourceOutputRootPath(arguments);
		final RelativePathExpression sourceOutputRelativePathExpression = getSourceOutputRelativePathExpression(arguments);
		final List<ModuleName> moduleNames = getModuleNames(arguments);

		try
		{
			new TranspilerApplication(new StandardErrorWarnings(), moduleNames, new RootPathAndExpression(modulesRootPath, modulesRelativePathExpression), new RootPathAndExpression(sourceOutputRootPath, sourceOutputRelativePathExpression)).execute();
		}
		catch (final Throwable e)
		{
			printUnexpectedFailureAndExitAbnormally(e);
		}
	}

	@NotNull
	private static Path getSourceOutputRootPath(@NotNull final OptionSet arguments)
	{
		return getArgumentAsPath(arguments, source_output_root);
	}

	@NotNull
	private static Path getModulesRootPath(@NotNull final OptionSet arguments)
	{
		return getArgumentAsPath(arguments, modules_root);
	}

	@NotNull
	private static Path getArgumentAsPath(@NotNull final OptionSet arguments, @NotNull final String argumentName)
	{
		try
		{
			final Path realPath = get(getArgumentValueAsStringOrFail(arguments, argumentName)).toRealPath();

			if (!exists(realPath))
			{
				throw new InvalidPathException(realPath.toString(), "Does not exist");
			}
			return realPath;
		}
		catch (final InvalidPathException | IOException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid path (%2$s)", argumentName, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	@NotNull
	private static RelativePathExpression getModulesRelativePathExpression(@NotNull final OptionSet arguments)
	{
		try
		{
			return new RelativePathExpression(getArgumentValueAsStringOrFail(arguments, modules_relative_path_expression));
		}
		catch (final IllegalRelativePathExpressionException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid modules relative path expression (%2$s)", modules_relative_path_expression, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	@NotNull
	private static RelativePathExpression getSourceOutputRelativePathExpression(@NotNull final OptionSet arguments)
	{
		try
		{
			return new RelativePathExpression(getArgumentValueAsStringOrFail(arguments, source_output_relative_path_expression));
		}
		catch (final IllegalRelativePathExpressionException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid source output relative path expression (%2$s)", source_output_relative_path_expression, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	@NotNull
	private static List<ModuleName> getModuleNames(@NotNull final OptionSet arguments)
	{
		try
		{
			//noinspection unchecked
			return (List<ModuleName>) arguments.nonOptionArguments();
		}
		catch (final OptionException e)
		{
			printErrorMessageAndHelpThenExit("Non-option arguments are invalid (%1$s)", e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	@NotNull
	private static String getArgumentValueAsStringOrFail(@NotNull final OptionSet arguments, @NotNull final String argumentName)
	{
		@Nullable final String value = (String) arguments.valueOf(argumentName);
		if (value == null)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s must be specified", argumentName);
			throw new ImpossibleStateException();
		}
		return value;
	}

	@SuppressWarnings("UseOfSystemOutOrSystemErr")
	private static void printExceptionAndHelpThenExit(@NotNull final Exception e)
	{
		printErrorMessageAndHelpThenExit("%1$s", e.getMessage());
	}

	@SuppressWarnings("UseOfSystemOutOrSystemErr")
	private static void printErrorMessageAndHelpThenExit(@NonNls @NotNull final String messageTemplate, @NotNull final String... arguments)
	{
		err.println(format(messageTemplate, arguments));
		printHelpThenExit(ExitCodeError);
	}

	@SuppressWarnings({"UseOfSystemOutOrSystemErr", "CallToSystemExit"})
	private static void printHelpThenExit(final int exitCode)
	{
		try
		{
			CommandLineArgumentsParser.printHelpOn(out);
		}
		catch (final IOException e)
		{
			printUnexpectedFailureAndExitAbnormally(e);
		}
		exit(exitCode);
	}

	@SuppressWarnings({"UseOfSystemOutOrSystemErr", "CallToPrintStackTrace", "CallToSystemExit"})
	private static void printUnexpectedFailureAndExitAbnormally(@NotNull final Throwable e)
	{
		err.println(format("Unexpected failure %1$s", e.getMessage()));
		e.printStackTrace();
		exit(ExitCodeError);
	}

	@NotNull
	private static String[] requiredArguments(@NotNull final String... requiredArguments)
	{
		return requiredArguments;
	}

	private ConsoleEntryPoint()
	{
	}
}

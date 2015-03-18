package com.java2c.transpiler.application;

import static com.java2c.utility.EnglishFormatter.format;
import com.java2c.transpiler.javaModules.*;
import com.java2c.utility.ImpossibleStateException;
import com.java2c.transpiler.warnings.StandardErrorWarnings;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import static java.io.File.pathSeparatorChar;
import static java.lang.System.*;
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
	private static final String classpath = "classpath";

	@SuppressWarnings("ConstantNamingConvention")
	@NotNull
	private static final String source_output_root = "source-output-root";

	@SuppressWarnings("ConstantNamingConvention")
	@NotNull
	private static final String source_output_relative_path_expression = "source-output-relative-path-expression";

	@NonNls
	@NotNull
	private static final OptionParser CommandLineArgumentsParser = new OptionParser();

	static
	{
		CommandLineArgumentsParser.posixlyCorrect(true);
		CommandLineArgumentsParser.accepts(help, "show help").forHelp();
		CommandLineArgumentsParser.accepts(modules_root, "modules modules path").withRequiredArg().describedAs("/Users/raphcohn/Documents/java2c").ofType(String.class);
		CommandLineArgumentsParser.accepts(modules_relative_path_expression, "modules relative path expression").withRequiredArg().describedAs("source/%m").ofType(String.class).defaultsTo("%m", "description");
		CommandLineArgumentsParser.accepts(classpath, "Class Path").withRequiredArg().describedAs("").ofType(String.class).withValuesSeparatedBy(pathSeparatorChar);
		CommandLineArgumentsParser.accepts(source_output_root, "c output root path").withRequiredArg().describedAs("/Users/raphcohn/Documents/java2c/output").ofType(String.class);
		CommandLineArgumentsParser.accepts(source_output_relative_path_expression, "c output relative path expression").withRequiredArg().describedAs("output/%m").ofType(String.class).defaultsTo("%m", "description");
		CommandLineArgumentsParser.nonOptions("module names").ofType(ModuleName.class);
	}

	public static final int ExitCodeHelp = 0;
	public static final int ExitCodeError = 1;

	public static void main(@NotNull final String... commandLineArguments)
	{
		final OptionSet arguments;
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
		final Path[] classPaths = getClassPaths(arguments);
		final RelativePathExpression sourceOutputRelativePathExpression = getSourceOutputRelativePathExpression(arguments);
		final List<ModuleName> moduleNames = getModuleNames(arguments);

		try
		{
			new TranspilerApplication(new StandardErrorWarnings(), moduleNames, new RootPathAndExpression(modulesRootPath, modulesRelativePathExpression), classPaths, new RootPathAndExpression(sourceOutputRootPath, sourceOutputRelativePathExpression)).execute();
		}
		catch (final Throwable e)
		{
			printUnexpectedFailureAndExitAbnormally(e);
		}
	}

	private static Path getSourceOutputRootPath(@NotNull final OptionSet arguments)
	{
		try
		{
			return get((String) arguments.valueOf(source_output_root)).toAbsolutePath();
		}
		catch (final InvalidPathException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid source output root path (%2$s)", source_output_root, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	private static Path getModulesRootPath(@NotNull final OptionSet arguments)
	{
		try
		{
			return get((String) arguments.valueOf(modules_root)).toAbsolutePath();
		}
		catch (final InvalidPathException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid modules root path (%2$s)", modules_root, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	private static RelativePathExpression getModulesRelativePathExpression(@NotNull final OptionSet arguments)
	{
		try
		{
			return new RelativePathExpression((String) arguments.valueOf(modules_relative_path_expression));
		}
		catch (final IllegalRelativePathExpressionException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid modules relative path expression (%2$s)", modules_relative_path_expression, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	private static Path[] getClassPaths(@NotNull final OptionSet arguments)
	{
		try
		{
			final List<?> classpathStrings = arguments.valuesOf(classpath);
			final int size = classpathStrings.size();
			final Path[] classPaths = new Path[size];
			for (int index = 0; index < size; index++)
			{
				final String classpathString = (String) classpathStrings.get(index);
				classPaths[index] = get(classpathString).toAbsolutePath();
			}
			return classPaths;
		}
		catch (final InvalidPathException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid path (%2$s)", classpath, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

	private static RelativePathExpression getSourceOutputRelativePathExpression(@NotNull final OptionSet arguments)
	{
		try
		{
			return new RelativePathExpression((String) arguments.valueOf(source_output_relative_path_expression));
		}
		catch (final IllegalRelativePathExpressionException e)
		{
			printErrorMessageAndHelpThenExit("Argument --%1$s is an invalid source output relative path expression (%2$s)", source_output_relative_path_expression, e.getMessage());
		}
		throw new ImpossibleStateException();
	}

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

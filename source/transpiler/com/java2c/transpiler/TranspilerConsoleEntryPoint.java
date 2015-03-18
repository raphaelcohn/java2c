package com.java2c.transpiler;

import com.java2c.transpiler.javaModules.*;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import static java.io.File.pathSeparatorChar;
import static java.lang.String.format;
import static java.lang.System.*;
import static java.nio.file.Paths.get;
import static java.util.Locale.ENGLISH;

public final class TranspilerConsoleEntryPoint
{
	private static final String help = "help";
	private static final String modules_root = "modules-root";
	private static final String modules_relative_path_expression = "modules-relative-path-expression";
	private static final String classpath = "classpath";
	private static final String source_output_root = "source-output-root";
	private static final String source_output_relative_path_expression = "source-output-relative-path-expression";
	private static final OptionParser CommandLineArgumentsParser = new OptionParser();
	static
	{
		CommandLineArgumentsParser.posixlyCorrect(true);
		CommandLineArgumentsParser.accepts(help, "show help").forHelp();
		CommandLineArgumentsParser.accepts(modules_root, "modules modules path").withRequiredArg().describedAs("/Users/raphcohn/Documents/java2c").ofType(String.class);
		CommandLineArgumentsParser.accepts(modules_relative_path_expression, "modules relative path expression").withRequiredArg().describedAs("source/%m").ofType(String.class).defaultsTo("%m", "description");
		CommandLineArgumentsParser.accepts(modules_relative_path_expression, "modules relative path expression").withRequiredArg().describedAs("source/%m").ofType(String.class).defaultsTo("%m", "description");
		CommandLineArgumentsParser.accepts(classpath, "classpath").withRequiredArg().describedAs("").ofType(String.class).withValuesSeparatedBy(pathSeparatorChar);
		CommandLineArgumentsParser.accepts(source_output_root, "c output root path").withRequiredArg().describedAs("/Users/raphcohn/Documents/java2c/output").ofType(String.class);
		CommandLineArgumentsParser.accepts(source_output_relative_path_expression, "c output relative path expression").withRequiredArg().describedAs("output/%m").ofType(String.class).defaultsTo("%m", "description");
		CommandLineArgumentsParser.nonOptions("module names").ofType(ModuleName.class);
	}

	public static void main(@NotNull final String... commandLineArguments)
	{
		final OptionSet arguments;
		try
		{
			arguments = CommandLineArgumentsParser.parse(commandLineArguments);
		}
		catch (OptionException e)
		{
			err.printf(e.getMessage() + '\n');
			printHelp(1);
			return;
		}

		if (arguments.has(help))
		{
			printHelp(2);
			return;
		}

		for(final String argument : requiredArguments(modules_root))
		{
			if (!arguments.has(argument))
			{
				err.printf(format(ENGLISH, "Missing required argument %1$s\n", argument));
				printHelp(1);
				return;
			}
		}

		final Path modulesRootPath;
		try
		{
			modulesRootPath = get((String) arguments.valueOf(modules_root)).toAbsolutePath();
		}
		catch (InvalidPathException e)
		{
			err.printf(format(ENGLISH, "Argument --%1$s is an invalid path (%2$s)\n", modules_root, e.getMessage()));
			printHelp(1);
			return;
		}

		final RelativePathExpression modulesRelativePathExpression;
		try
		{
			modulesRelativePathExpression = new RelativePathExpression((String) arguments.valueOf(modules_relative_path_expression));
		}
		catch (IllegalRelativePathExpressionException e)
		{
			err.printf(format(ENGLISH, "Argument --%1$s is an invalid expression (%2$s)\n", modules_relative_path_expression, e.getMessage()));
			printHelp(1);
			return;
		}

		final Path sourceOutputRootPath;
		try
		{
			sourceOutputRootPath = get((String) arguments.valueOf(source_output_root)).toAbsolutePath();
		}
		catch (InvalidPathException e)
		{
			err.printf(format(ENGLISH, "Argument --%1$s is an invalid path (%2$s)\n", source_output_root, e.getMessage()));
			printHelp(1);
			return;
		}

		final Path[] classPaths;
		try
		{
			final List<?> classpathStrings = arguments.valuesOf(classpath);
			classPaths = new Path[classpathStrings.size()];
			for (int index = 0; index < classpathStrings.size(); index++)
			{
				final String classpathString = (String) classpathStrings.get(index);
				classPaths[index] = get(classpathString).toAbsolutePath();
			}
		}
		catch (InvalidPathException e)
		{
			err.printf(format(ENGLISH, "Argument --%1$s is an invalid path (%2$s)\n", classpath, e.getMessage()));
			printHelp(1);
			return;
		}

		final RelativePathExpression sourceOutputRelativePathExpression;
		try
		{
			sourceOutputRelativePathExpression = new RelativePathExpression((String) arguments.valueOf(source_output_relative_path_expression));
		}
		catch (IllegalRelativePathExpressionException e)
		{
			err.printf(format(ENGLISH, "Argument --%1$s is an invalid expression (%2$s)\n", source_output_relative_path_expression, e.getMessage()));
			printHelp(1);
			return;
		}

		final List<ModuleName> moduleNames;
		try
		{
			//noinspection unchecked
			moduleNames = (List<ModuleName>) arguments.nonOptionArguments();
		}
		catch (OptionException e)
		{
			err.printf(format(ENGLISH, "Non-option arguments are invalid (%1$s)\n", e.getMessage()));
			printHelp(1);
			return;
		}

		final Application application = new Application(moduleNames, new RootPathAndExpression(modulesRootPath, modulesRelativePathExpression), classPaths, new RootPathAndExpression(sourceOutputRootPath, sourceOutputRelativePathExpression));

		try
		{
			application.execute();
		}
		catch (IllegalRelativePathException | IllegalRelativePathExpressionException e)
		{
			err.printf(e.getMessage());
			printHelp(1);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			err.printf(format(ENGLISH, "Unexpected failure %1$s\n", e.getMessage()));
		}
	}

	private static void printHelp(final int exitCode)
	{
		try
		{
			CommandLineArgumentsParser.printHelpOn(out);
		}
		catch (IOException e)
		{
			err.printf(format(ENGLISH, "Unexpected failure printing help %1$s\n", e.getMessage()));
		}
		exit(exitCode);
	}

	@NotNull
	private static String[] requiredArguments(@NotNull final String... requiredArguments)
	{
		return requiredArguments;
	}

	private TranspilerConsoleEntryPoint()
	{
	}
}

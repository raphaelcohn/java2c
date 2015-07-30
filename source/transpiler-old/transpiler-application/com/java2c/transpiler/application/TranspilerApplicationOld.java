/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.application;

import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreterFactory;
import com.compilerUser.exceptions.FatalCompilationException;
import com.compilerUser.moduleCompiler.JavaModuleCompiler;
import com.compilerUser.moduleName.ModuleName;
import com.compilerUser.moduleCompiler.WarningsAdaptingDiagnosticListener;
import com.compilerUser.processors.CodeTreeUserAdaptingProcessor;
import com.compilerUser.elementHandlers.ElementHandlingCodeTreeUser;
import com.compilerUser.javaSourceFiles.JavaSourceFilesFinder;
import com.compilerUser.pathExpressions.IllegalRelativePathException;
import com.compilerUser.pathExpressions.IllegalRelativePathExpressionException;
import com.compilerUser.pathExpressions.RelativePathExpression;
import com.compilerUser.pathExpressions.RootPathAndExpression;
import com.compilerUser.elementHandlers.RootElementHandler;
import com.java2c.transpiler.elementHandlers.PackageElementHandler;
import com.compilerUser.elementHandlers.DispatchToTypeElementHandler;
import com.java2c.transpiler.typeElementHandlers.AnnotationTypeElementHandler;
import com.java2c.transpiler.typeElementHandlers.ClassTypeElementHandler;
import com.java2c.transpiler.typeElementHandlers.EnumTypeElementHandler;
import com.java2c.transpiler.typeElementHandlers.InterfaceTypeElementHandler;
import com.compilerUser.warnings.Warnings;
import com.compilerUser.exceptions.ImpossibleStateException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.tools.JavaCompiler;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import static com.compilerUser.text.EnglishFormatter.format;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.delete;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public final class TranspilerApplicationOld
{
	@SuppressWarnings("NullableProblems")
	@NotNull
	private static final RelativePathExpression ModuleNamePathExpression;

	static
	{
		try
		{
			ModuleNamePathExpression = new RelativePathExpression("%m");
		}
		catch (final IllegalRelativePathExpressionException ignored)
		{
			throw new ImpossibleStateException();
		}
	}

	@NotNull
	@NonNls
	private static final String TemporaryFolderPrefix = "java2c-";

	@NotNull
	private final Warnings warnings;

	@NotNull
	private final List<ModuleName> moduleNames;

	@NotNull
	private final RootPathAndExpression moduleRoot;

	@NotNull
	private final RootPathAndExpression sourceOutput;

	@NotNull
	private final Collection<Path> additionalClassPath;

	@NotNull
	private final JavaModuleCompiler javaModuleCompiler;

	@NotNull
	private final CodeTreeUserAdaptingProcessor processor;

	@SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
	public TranspilerApplicationOld(@NotNull final Warnings warnings, @NotNull final List<ModuleName> moduleNames, @NotNull final RootPathAndExpression moduleRoot, @NotNull final RootPathAndExpression sourceOutput, @NotNull final Collection<Path> additionalClassPath)
	{
		this.warnings = warnings;
		this.moduleNames = moduleNames;
		this.moduleRoot = moduleRoot;
		this.sourceOutput = sourceOutput;
		this.additionalClassPath = additionalClassPath;

		javaModuleCompiler = new JavaModuleCompiler(warnings, new WarningsAdaptingDiagnosticListener(warnings), getJavaCompiler(), new JavaSourceFilesFinder(warnings));

		processor = new CodeTreeUserAdaptingProcessor
		(
			new ElementHandlingCodeTreeUser<>
			(
				new RootElementHandler<>
				(
					new PackageElementHandler(),
					new DispatchToTypeElementHandler<>
					(
						new AnnotationTypeElementHandler(),
						new InterfaceTypeElementHandler(),
						new EnumTypeElementHandler(),
						new ClassTypeElementHandler()
					)
				),
				new OurAbstractSyntaxTreeInterpreterFactory()
			)
		);
	}

	public void execute()
	{
		final Path classOutputRootPath = createTemporaryClassOutputPath();
		try
		{
			useClassOutputRootPath(classOutputRootPath);
		}
		catch (final IllegalRelativePathException | FatalCompilationException e)
		{
			warnings.fatal(e);
		}
		finally
		{
			try
			{
				delete(classOutputRootPath);
			}
			catch (final IOException ignored)
			{
			}
		}
	}

	private void useClassOutputRootPath(@NotNull final Path classOutputRootPath) throws IllegalRelativePathException, FatalCompilationException
	{
		final RootPathAndExpression classOutput = new RootPathAndExpression(classOutputRootPath, ModuleNamePathExpression);

		for (final ModuleName moduleName : moduleNames)
		{
			final Path sourceOutputPath = sourceOutput.resolvePath(moduleName);
			final Path classOutputPath = classOutput.resolvePath(moduleName);
			final Path sourcePath = moduleRoot.resolvePath(moduleName);

			// TODO: XXX add all modules to source, or how else to manage dependencies?;

			javaModuleCompiler.compile(additionalClassPath, sourcePath, sourceOutputPath, classOutputPath, processor);
		}
	}

	@NotNull
	private static Path createTemporaryClassOutputPath()
	{
		final Path classOutputRootPath;
		try
		{
			classOutputRootPath = createTempDirectory(TemporaryFolderPrefix);
		}
		catch (final IOException e)
		{
			throw new IllegalStateException(format("Could not create a temporary folder for java2c because of '%1$s'", e.getMessage()), e);
		}
		return classOutputRootPath;
	}

	@NotNull
	private static JavaCompiler getJavaCompiler()
	{
		@Nullable final JavaCompiler javaCompiler = getSystemJavaCompiler();
		if (javaCompiler == null)
		{
			throw new IllegalStateException("There is no system Java compiler");
		}
		return javaCompiler;
	}
}

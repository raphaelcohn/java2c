/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler;

import com.java2c.transpiler.c.VariableDeclaration;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.QualifiedNameable;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.compilerUser.text.CharsetHelper.Utf8;
import static java.lang.String.format;
import static java.util.Locale.ENGLISH;
import static java.util.regex.Pattern.compile;
import static javax.lang.model.element.Modifier.STRICTFP;

public class CMaker
{
	@NotNull private static final Pattern DotMatch = compile("\\.");
	@NotNull private final CFileCreator cFileCreator;

	public CMaker(@NotNull final CFileCreator cFileCreator)
	{
		this.cFileCreator = cFileCreator;
	}

	public void makeFiles(@NotNull final QualifiedNameable qualifiedNameable, final boolean isPackage, @NotNull final List<String> libraryIncludes, @NotNull final List<String> localIncludes, @NotNull final List<VariableDeclaration> publicConstants) throws IOException
	{
		final Set<Modifier> modifiers = qualifiedNameable.getModifiers();

		// strictfp in essence requires that we use float and double
		// w/o strictfp, we could use float_t and double_t, which are normally 80-bit long double
		// in practice, this is going to confuse the hell out of users
		if (modifiers.contains(STRICTFP))
		{
			/*
			The effect of the strictfp modifier is to make all float or double expressions within the class declaration (including within variable initializers,
			instance initializers, static initializers, and constructors) be explicitly FP-strict (§15.4).
			This implies that all methods declared in the class, and all nested types declared in the class, are implicitly strictfp.

				In other words, use double_t / float_t types from math.h for local variables, if NOT using strictfp
				Generate a cast from double_t to double and float_t to float; need to check that the thing we're casting is, of course, actually a local variable of the relevant type... and cast order precedence is maintained (if the user then casts to something else
			 */

			/*
				On an interface, makes all implementations of all methods strict...
			 */
		}



		final String qualifiedName = qualifiedNameable.getQualifiedName().toString();
		// Includes to other modules will need to use a relative path syntax...

		final File headerFile = cFileCreator.headerFilePath(qualifiedName, isPackage);
		try(final OutputStream outputStream = new FileOutputStream(headerFile, true))
		{
			try (final Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, Utf8), 4096))
			{
				// eg _COM_STORMMQ_PACKAGE_CLASS_H_
				final String headerDefinedMacro = headerDefinedMacro(headerFile);
				writer.write(format(ENGLISH, "#ifndef %1$s\n", headerDefinedMacro));
				writer.write(format(ENGLISH, "#define %1$s\n", headerDefinedMacro));

				writer.write("#ifdef __cplusplus\n");
				writer.write("extern \"C\" {\n");
				writer.write("#endif\n");

				for (String libraryInclude : libraryIncludes)
				{
					writer.write(format(ENGLISH, "#include <%1$s>\n", libraryInclude));
				}

				for (String localInclude : localIncludes)
				{
					writer.write(format(ENGLISH, "#include <%1$s>\n", localInclude));
				}



				// Define PUBLIC constants, incompletely

				// Tentative definitions of PUBLIC things can go here...

				writer.write("#ifdef __cplusplus\n");
				writer.write("}\n");
				writer.write("#endif\n");

				writer.write("#endif\n");
			}
		}

		final File sourceFile = cFileCreator.sourceFilePath(qualifiedName, true);
		try(final OutputStream outputStream = new FileOutputStream(sourceFile, true))
		{
			try (final Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, Utf8), 4096))
			{
				writer.write(format(ENGLISH, "#include \"%1$s\"\n", headerFile.getName()));

				// Tentative definitions of ALL things can go here... remember they need to include attributes

				// Actual definitions can go here
			}
		}
	}

	private static String headerDefinedMacro(@NotNull final File headerFile)
	{
		return '_' + replacePeriodWithUnderscore(fileNameToUpperCase(headerFile)) + "_H_";
	}

	@NotNull
	private static CharSequence fileNameToUpperCase(@NotNull final File headerFile)
	{
		return headerFile.getName().toUpperCase(ENGLISH);
	}

	@NotNull
	private static String replacePeriodWithUnderscore(@NotNull final CharSequence original)
	{
		return DotMatch.matcher(original).replaceAll("_");
	}
}

package com.java2c.transpiler.conversion.elementConverters;

import com.java2c.transpiler.conversion.CMaker;
import com.java2c.transpiler.conversion.typeResolution.TypeResolver;
import com.java2c.transpiler.conversion.c.VariableDeclaration;
import com.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.*;
import java.io.IOException;
import java.util.Collections;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class TopLevelInterfaceElementConverter implements ElementConverter<TypeElement>
{
	@NotNull private final CMaker cMaker;

	public TopLevelInterfaceElementConverter(@NotNull final CMaker cMaker)
	{
		this.cMaker = cMaker;
	}

	@Override
	public void convert(@NotNull final Warnings warnings, @NotNull final TypeResolver typeResolver, @NotNull final TypeElement element) throws ConversionException
	{
		// No name for local and anonymous classes, which is annoying
		final CategorisedClassMembers categorisedClassMembers = new CategorisedClassMembers(typeResolver, element, element.getQualifiedName());

		categorisedClassMembers.processStaticFields();

		try
		{
			cMaker.makeFiles(element, true, Collections.<String>emptyList(), Collections.<String>emptyList(), Collections.<VariableDeclaration>emptyList());
		}
		catch (IOException e)
		{
			throw new ConversionException(format(ENGLISH, "Could not create header or source files for %1$s", element.getQualifiedName().toString()), e);
		}
	}
}

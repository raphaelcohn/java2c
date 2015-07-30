package com.java2c.transpiler.elementConverters;

import com.java2c.transpiler.CMaker;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;


public final class TopLevelInterfaceElementConverter implements ElementConverter<TypeElement>
{
	@NotNull private final CMaker cMaker;

	public TopLevelInterfaceElementConverter(@NotNull final CMaker cMaker)
	{
		this.cMaker = cMaker;
	}

	@Override
	public void convert(@NonNls @NotNull final Messager messager, @NotNull final TypeElement element) throws ConversionException
	{
//		// No name for local and anonymous classes, which is annoying
//		final CategorisedClassMembers categorisedClassMembers = new CategorisedClassMembers(typeResolver, element, element.getQualifiedName());
//
//		categorisedClassMembers.processStaticFields();
//
//		try
//		{
//			cMaker.makeFiles(element, true, Collections.<String>emptyList(), Collections.<String>emptyList(), Collections.<VariableDeclaration>emptyList());
//		}
//		catch (final IOException e)
//		{
//			throw new ConversionException(format("Could not create header or source files for %1$s", element.getQualifiedName().toString()), e);
//		}
	}
}

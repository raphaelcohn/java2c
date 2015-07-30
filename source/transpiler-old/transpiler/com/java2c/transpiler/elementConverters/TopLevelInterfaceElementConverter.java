/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

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

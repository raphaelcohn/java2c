/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.exceptions;

import com.compilerUser.elementHandlers.AbstractSyntaxTreeInterpreter;
import com.compilerUser.exceptions.IncorrectSourceCodeException;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.TypeElement;

import static com.compilerUser.text.EnglishFormatter.format;

public final class ClassAnnotationNotPermittedOnThisTypeElementException extends IncorrectSourceCodeException
{
	public ClassAnnotationNotPermittedOnThisTypeElementException(@NotNull final Class<?> annotationType, @NotNull final TypeElement typeElement, @NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter)
	{
		super(format("The annotation type '%1$s' is not permitted on a type element of type kind '%2$s' ('%3$s')", annotationType.getClass().getSimpleName(), typeElement.getKind().name(), abstractSyntaxTreeInterpreter.getCanonicalClassName(typeElement)));
	}
}

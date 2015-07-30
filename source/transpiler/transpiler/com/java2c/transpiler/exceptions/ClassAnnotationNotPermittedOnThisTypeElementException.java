package com.java2c.transpiler.exceptions;

import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.TypeElement;

import static com.compilerUser.text.EnglishFormatter.format;

public final class ClassAnnotationNotPermittedOnThisTypeElementException extends IncorrectSourceCodeException
{
	public ClassAnnotationNotPermittedOnThisTypeElementException(@NotNull final Class<?> annotationType, @NotNull final TypeElement typeElement, @NotNull final OurAbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter)
	{
		super(format("The annotation type '%1$s' is not permitted on a type element of type kind '%2$s' ('%3$s')", annotationType.getClass().getSimpleName(), typeElement.getKind().name(), abstractSyntaxTreeInterpreter.getCanonicalClassName(typeElement)));
	}
}

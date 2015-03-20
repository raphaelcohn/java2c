package com.java2c.transpiler.elementHandlers.typeElementHandlers;

import com.java2c.model.types.scalars.Scalar;
import com.java2c.transpiler.AbstractSyntaxTreeInterpreter;
import com.java2c.transpiler.elementHandlers.ElementHandler;
import com.java2c.transpiler.exceptions.ClassAnnotationNotPermittedOnThisTypeElementException;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.TypeElement;

public abstract class AbstractTypeElementHandler implements TypeElementHandler
{
	protected AbstractTypeElementHandler()
	{
	}

	@Override
	public void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement> dispatchingTypeElementHandler)
	{
		guardScalarAnnotationNotPresent(abstractSyntaxTreeInterpreter, element);
	}

	// ? Move to AbstractSyntaxTreeInterpreter
	private static void guardScalarAnnotationNotPresent(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element)
	{
		if (element.getAnnotation(Scalar.class) != null)
		{
			throw new ClassAnnotationNotPermittedOnThisTypeElementException(Scalar.class, element, abstractSyntaxTreeInterpreter);
		}
	}
}

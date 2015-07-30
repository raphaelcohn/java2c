package com.java2c.transpiler.typeElementHandlers;

import com.compilerUser.elementHandlers.ElementHandler;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.TypeElement;

public final class EnumTypeElementHandler extends AbstractTypeElementHandler
{
	@Override
	public void handle(@NotNull final OurAbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement, OurAbstractSyntaxTreeInterpreter> dispatchingTypeElementHandler)
	{
		super.handle(abstractSyntaxTreeInterpreter, element, dispatchingTypeElementHandler);

		abstractSyntaxTreeInterpreter.guardClassInheritsFromAbstractCType(element);
	}
}

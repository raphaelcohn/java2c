package com.java2c.transpiler.typeElementHandlers;

import com.compilerUser.elementHandlers.ElementHandler;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;

public final class InterfaceTypeElementHandler extends AbstractTypeElementHandler
{
	@Override
	public void handle(@NotNull final OurAbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement, OurAbstractSyntaxTreeInterpreter> dispatchingTypeElementHandler)
	{
		super.handle(abstractSyntaxTreeInterpreter, element, dispatchingTypeElementHandler);

		abstractSyntaxTreeInterpreter.guardInterfaceInheritsFromCType(element);





		final List<? extends Element> enclosedElements = element.getEnclosedElements();
		for (Element enclosedElement : enclosedElements)
		{
			if (enclosedElement instanceof TypeElement)
			{
				dispatchingTypeElementHandler.handle(abstractSyntaxTreeInterpreter, (TypeElement) enclosedElement);
			}
		}
	}
}

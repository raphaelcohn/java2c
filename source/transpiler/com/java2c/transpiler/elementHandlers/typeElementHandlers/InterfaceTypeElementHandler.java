package com.java2c.transpiler.elementHandlers.typeElementHandlers;

import com.java2c.transpiler.AbstractSyntaxTreeInterpreter;
import com.java2c.transpiler.elementHandlers.ElementHandler;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.List;

public final class InterfaceTypeElementHandler extends AbstractTypeElementHandler
{
	@Override
	public void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement> dispatchingTypeElementHandler)
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

package com.compilerUser.elementHandlers.typeElementHandlers;

import com.compilerUser.elementHandlers.ElementHandler;
import com.compilerUser.elementHandlers.AbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.TypeElement;

public interface TypeElementHandler<A extends AbstractSyntaxTreeInterpreter>
{
	void handle(@NotNull final A abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement, A> dispatchingTypeElementHandler);
}

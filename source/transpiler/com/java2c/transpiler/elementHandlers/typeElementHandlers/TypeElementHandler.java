package com.java2c.transpiler.elementHandlers.typeElementHandlers;

import com.java2c.transpiler.AbstractSyntaxTreeInterpreter;
import com.java2c.transpiler.elementHandlers.ElementHandler;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.TypeElement;

public interface TypeElementHandler
{
	void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement> dispatchingTypeElementHandler);
}

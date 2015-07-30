package com.compilerUser.elementHandlers;

import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Element;

public interface ElementHandler<E extends Element, A extends AbstractSyntaxTreeInterpreter>
{
	void handle(@NotNull final A abstractSyntaxTreeInterpreter, @NotNull final E element);
}

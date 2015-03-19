package com.java2c.transpiler.elementHandlers;

import com.java2c.transpiler.AbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Element;

public interface ElementHandler<E extends Element>
{
	void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final E element);
}

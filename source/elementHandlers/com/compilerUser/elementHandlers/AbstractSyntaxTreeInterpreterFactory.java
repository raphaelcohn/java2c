package com.compilerUser.elementHandlers;

import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public interface AbstractSyntaxTreeInterpreterFactory<A extends AbstractSyntaxTreeInterpreter>
{
	@NotNull
	A create(@NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees);
}

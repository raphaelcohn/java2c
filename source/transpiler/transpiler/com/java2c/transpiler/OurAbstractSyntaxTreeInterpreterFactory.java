package com.java2c.transpiler;

import com.compilerUser.elementHandlers.AbstractSyntaxTreeInterpreterFactory;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public final class OurAbstractSyntaxTreeInterpreterFactory implements AbstractSyntaxTreeInterpreterFactory<OurAbstractSyntaxTreeInterpreter>
{
	@NotNull
	@Override
	public OurAbstractSyntaxTreeInterpreter create(@NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees)
	{
		return new OurAbstractSyntaxTreeInterpreter(typeUtilities, elementUtilities, trees);
	}
}

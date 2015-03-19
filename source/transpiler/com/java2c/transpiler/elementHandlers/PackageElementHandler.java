package com.java2c.transpiler.elementHandlers;

import com.java2c.transpiler.AbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.PackageElement;

public final class PackageElementHandler implements ElementHandler<PackageElement>
{
	@Override
	public void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final PackageElement element)
	{
		// 1 PackageElements do not contain sub packages
		// 2 They really aren't of much use except for 'package-info.java'
	}
}

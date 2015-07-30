package com.java2c.transpiler.elementHandlers;

import com.compilerUser.elementHandlers.ElementHandler;
import com.java2c.model.attributes.packages.PackageGccCompilationOptions;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.QualifiedNameable;

public final class PackageElementHandler implements ElementHandler<PackageElement, OurAbstractSyntaxTreeInterpreter>
{
	@Override
	public void handle(@NotNull final OurAbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final PackageElement element)
	{
		// 1 PackageElements do not contain sub packages
		// 2 They really aren't of much use except for 'package-info.java'

		@Nullable final PackageGccCompilationOptions packageGccCompilationOptions = element.getAnnotation(PackageGccCompilationOptions.class);
		if (packageGccCompilationOptions == null)
		{
			return;
		}
		@NotNull final String[] optimisations = packageGccCompilationOptions.value();
		for (final String optimisation : optimisations)
		{
			// TODO: Do something with the optimisation - write to a file somewhere, perhaps!

			elementNameToUnderscoreName(element);
		}
	}





	@NotNull
	public static String elementNameToUnderscoreName(@NotNull final QualifiedNameable element)
	{
		return element.getQualifiedName().toString().replace('.', '_');
	}
}

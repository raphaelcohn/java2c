package com.java2c.transpiler.elementHandlers;

import com.java2c.transpiler.AbstractSyntaxTreeInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import static com.compilerUser.text.EnglishFormatter.format;

public final class RootElementHandler implements ElementHandler<Element>
{
	@NotNull
	private final ElementHandler<PackageElement> packageElementHandler;

	@NotNull
	private final ElementHandler<TypeElement> typeElementHandler;

	public RootElementHandler(@SuppressWarnings("TypeMayBeWeakened") @NotNull final ElementHandler<PackageElement> packageElementHandler, @SuppressWarnings("TypeMayBeWeakened") @NotNull final ElementHandler<TypeElement> typeElementHandler)
	{
		this.packageElementHandler = packageElementHandler;
		this.typeElementHandler = typeElementHandler;
	}

	@SuppressWarnings("ChainOfInstanceofChecks")
	@Override
	public void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final Element element)
	{
		if (element instanceof TypeElement)
		{
			typeElementHandler.handle(abstractSyntaxTreeInterpreter, (TypeElement) element);
		}
		else if (element instanceof PackageElement)
		{
			packageElementHandler.handle(abstractSyntaxTreeInterpreter, (PackageElement) element);
		}
		else
		{
			throw new IllegalStateException(format("Did not expect a root element of class '%1$s'", element.getClass()));
		}
	}

}

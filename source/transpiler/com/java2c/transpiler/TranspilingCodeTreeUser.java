package com.java2c.transpiler;

import com.java2c.codeTreeUsers.CodeTreeUser;
import com.java2c.transpiler.elementHandlers.RootElementHandler;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

import static javax.tools.Diagnostic.Kind.ERROR;

public final class TranspilingCodeTreeUser implements CodeTreeUser
{
	@NotNull
	private final RootElementHandler rootElementHandler;

	public TranspilingCodeTreeUser(@NotNull final RootElementHandler rootElementHandler)
	{
		this.rootElementHandler = rootElementHandler;
	}

	@Override
	public void process(@NotNull final Messager messager, @NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees, @NotNull final Set<? extends Element> rootElements)
	{
		final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter = new AbstractSyntaxTreeInterpreter(typeUtilities, elementUtilities, trees);

		for (final Element rootElement : rootElements)
		{
			try
			{
				rootElementHandler.handle(abstractSyntaxTreeInterpreter, rootElement);
			}
			catch (final RuntimeException e)
			{
				messager.printMessage(ERROR, e.getMessage(), rootElement);
			}
		}
	}
}

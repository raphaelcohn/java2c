package com.compilerUser.elementHandlers;

import com.compilerUser.codeTreeUsers.CodeTreeUser;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

import static javax.tools.Diagnostic.Kind.ERROR;

public final class ElementHandlingCodeTreeUser<A extends AbstractSyntaxTreeInterpreter> implements CodeTreeUser
{
	@NotNull
	private final RootElementHandler<A> rootElementHandler;

	@NotNull
	private final AbstractSyntaxTreeInterpreterFactory<A> abstractSyntaxTreeInterpreterFactory;

	public ElementHandlingCodeTreeUser(@NotNull final RootElementHandler<A> rootElementHandler, @NotNull final AbstractSyntaxTreeInterpreterFactory<A> abstractSyntaxTreeInterpreterFactory)
	{
		this.rootElementHandler = rootElementHandler;
		this.abstractSyntaxTreeInterpreterFactory = abstractSyntaxTreeInterpreterFactory;
	}

	@Override
	public void process(@NotNull final Messager messager, @NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees, @NotNull final Set<? extends Element> rootElements)
	{
		final A abstractSyntaxTreeInterpreter = abstractSyntaxTreeInterpreterFactory.create(typeUtilities, elementUtilities, trees);

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

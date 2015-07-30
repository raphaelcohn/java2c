package com.compilerUser.elementHandlers;

import com.compilerUser.elementHandlers.typeElementHandlers.TypeElementHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.EnumMap;
import java.util.Map;

import static com.compilerUser.text.EnglishFormatter.format;
import static javax.lang.model.element.ElementKind.*;

public final class DispatchToTypeElementHandler<A extends AbstractSyntaxTreeInterpreter> implements ElementHandler<TypeElement, A>
{
	@NotNull
	private final Map<ElementKind, TypeElementHandler<A>> dispatchTable;

	public DispatchToTypeElementHandler(@NotNull final TypeElementHandler<A> annotationTypeElementHandler, @NotNull final TypeElementHandler<A> interfaceTypeElementHandler, @NotNull final TypeElementHandler<A> enumTypeElementHandler, @NotNull final TypeElementHandler<A> classTypeElementHandler)
	{
		dispatchTable = new EnumMap<>(ElementKind.class);
		dispatchTable.put(ANNOTATION_TYPE, annotationTypeElementHandler);
		dispatchTable.put(INTERFACE, interfaceTypeElementHandler);
		dispatchTable.put(ENUM, enumTypeElementHandler);
		dispatchTable.put(CLASS, classTypeElementHandler);
	}

	@Override
	public void handle(@NotNull final A abstractSyntaxTreeInterpreter, @NotNull final TypeElement element)
	{
		final ElementKind elementKind = element.getKind();
		@Nullable final TypeElementHandler<A> typeElementHandlerToDispatchTo = dispatchTable.get(elementKind);
		if (typeElementHandlerToDispatchTo == null)
		{
			throw new IllegalStateException(format("Did not expect a TypeElement of ElementKind '%1$s'", elementKind.name()));
		}
		typeElementHandlerToDispatchTo.handle(abstractSyntaxTreeInterpreter, element, this);
	}
}

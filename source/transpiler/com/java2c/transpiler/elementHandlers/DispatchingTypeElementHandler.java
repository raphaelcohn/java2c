package com.java2c.transpiler.elementHandlers;

import com.java2c.transpiler.AbstractSyntaxTreeInterpreter;
import com.java2c.transpiler.elementHandlers.typeElementHandlers.TypeElementHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.EnumMap;
import java.util.Map;

import static com.java2c.utility.EnglishFormatter.format;
import static javax.lang.model.element.ElementKind.*;

public final class DispatchingTypeElementHandler implements ElementHandler<TypeElement>
{
	@NotNull
	private final Map<ElementKind, TypeElementHandler> dispatchTable;

	public DispatchingTypeElementHandler(@NotNull final TypeElementHandler annotationTypeElementHandler, @NotNull final TypeElementHandler interfaceTypeElementHandler, @NotNull final TypeElementHandler enumTypeElementHandler, @NotNull final TypeElementHandler classTypeElementHandler)
	{
		dispatchTable = new EnumMap<>(ElementKind.class);
		dispatchTable.put(ANNOTATION_TYPE, annotationTypeElementHandler);
		dispatchTable.put(INTERFACE, interfaceTypeElementHandler);
		dispatchTable.put(ENUM, enumTypeElementHandler);
		dispatchTable.put(CLASS, classTypeElementHandler);
	}

	@Override
	public void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element)
	{
		final ElementKind elementKind = element.getKind();
		@Nullable final TypeElementHandler typeElementHandlerToDispatchTo = dispatchTable.get(elementKind);
		if (typeElementHandlerToDispatchTo == null)
		{
			throw new IllegalStateException(format("Did not expect a TypeElement of ElementKind '%1$s'", elementKind.name()));
		}
		typeElementHandlerToDispatchTo.handle(abstractSyntaxTreeInterpreter, element, this);
	}
}

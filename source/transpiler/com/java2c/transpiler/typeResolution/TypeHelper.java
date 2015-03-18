package com.java2c.transpiler.typeResolution;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public final class TypeHelper
{
	@NotNull private final Types typeUtilities;
	@NotNull private final Elements elementUtilities;
	@NotNull public final WildcardType wildcardTypeAny;

	public TypeHelper(@NotNull final Types typeUtilities, @NotNull final Elements elementUtilities)
	{
		this.typeUtilities = typeUtilities;
		this.elementUtilities = elementUtilities;
		wildcardTypeAny = typeUtilities.getWildcardType(null, null);
	}

	@NotNull
	public TypeElement typeElementFor(@NotNull final Class<?> clazz)
	{
		return elementUtilities.getTypeElement(clazz.getCanonicalName());
	}

	@NotNull
	public TypeMirror typeFor(@NotNull final Class<?> clazz)
	{
		return typeElementFor(clazz).asType();
	}

	@Nullable
	public TypeElement typeElementFor(@NotNull final TypeMirror fieldType)
	{
		return (TypeElement) typeUtilities.asElement(fieldType);
	}

	public boolean isSubtype(@NotNull final TypeMirror fieldType, @NotNull final TypeMirror primitiveTypeElementType)
	{
		return typeUtilities.isSubtype(fieldType, primitiveTypeElementType);
	}

	@NotNull
	public WildcardType wildcardTypeSuper(@NotNull final TypeMirror superBound)
	{
		return typeUtilities.getWildcardType(null, superBound);
	}

	@NotNull
	public WildcardType wildcardTypeExtends(@NotNull final TypeMirror extendsBound)
	{
		return typeUtilities.getWildcardType(extendsBound, null);
	}
}

package com.java2c.transpiler.typeResolution;

import com.java2c.utility.EnglishFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.OutputStreamWriter;
import java.lang.management.BufferPoolMXBean;
import java.util.List;

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
		final String canonicalName = clazz.getCanonicalName();
		@Nullable final TypeElement typeElement = elementUtilities.getTypeElement(canonicalName);
		if (typeElement == null)
		{
			throw new IllegalArgumentException(EnglishFormatter.format("No known TypeElement for clazz '%1$s'; is the CLASS_PATH set correctly?", canonicalName));
		}
		return typeElement;
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

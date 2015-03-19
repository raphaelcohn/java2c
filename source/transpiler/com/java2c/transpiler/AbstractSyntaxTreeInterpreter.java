package com.java2c.transpiler;

import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.java2c.utility.EnglishFormatter.format;

public final class AbstractSyntaxTreeInterpreter
{
	/*
		Element
			|- PackageElement
			|- TypeElement (class, interface)
			|- TypeParameterElement
			|- VariableElement (local, field, constant, method parameter)
			|- ExecutableElement (method, constructor, initialiser)

		TypeMirror
			|- PrimitiveType
				|- eg boolean
			|- ReferenceType (anything)
				|- ArrayType
				|- NullType
				|- NoType
					|- eg package
					|- eg void
					|- eg Object's superclass
				|- DeclaredType (eg class, interface) (invocation of a TypeElement, say)
					|- ErrorType (bad class or interface)
			|- WildcardType
			|- UnionType (multicatch of Exceptions)
			|- TypeVariable (the bit in <A> generics); horrid
			|- ExecutableType (method, constructor or initialiser)

		AnnotationMirror

		Trees / TreePath

		Tree
			|- SwitchTree
			|- ForLoopTree
			|- CatchTree
			etc
	 */

	@NotNull
	private final Types typeUtilities;

	@NotNull
	private final Elements elementUtilities;

	@NotNull
	private final Trees trees;

	public AbstractSyntaxTreeInterpreter(@NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees)
	{
		this.typeUtilities = typeUtilities;
		this.elementUtilities = elementUtilities;
		this.trees = trees;
	}

	// null for PackageElement
	@Nullable
	public TreePath treePathForElement(@NotNull final Element element)
	{
		return trees.getPath(element);
	}

	@NotNull
	public TypeElement typeElementFor(@NotNull final Class<?> clazz)
	{
		final String canonicalName = clazz.getCanonicalName();
		@Nullable final TypeElement typeElement = elementUtilities.getTypeElement(canonicalName);
		if (typeElement == null)
		{
			throw new IllegalArgumentException(format("No known TypeElement for clazz '%1$s'; is the CLASS_PATH set correctly?", canonicalName));
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

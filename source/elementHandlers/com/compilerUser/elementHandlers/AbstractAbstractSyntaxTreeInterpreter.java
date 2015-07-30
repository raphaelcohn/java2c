package com.compilerUser.elementHandlers;

import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.List;

import static com.compilerUser.text.EnglishFormatter.format;

public abstract class AbstractAbstractSyntaxTreeInterpreter implements AbstractSyntaxTreeInterpreter
{
	@NotNull
	protected final Types typeUtilities;

	@NotNull
	protected final Elements elementUtilities;

	@NotNull
	protected final Trees trees;

	protected AbstractAbstractSyntaxTreeInterpreter(@NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees)
	{
		this.typeUtilities = typeUtilities;
		this.elementUtilities = elementUtilities;
		this.trees = trees;
	}

	@NotNull
	public final String getCanonicalClassName(@NotNull final TypeElement element)
	{
		return elementUtilities.getBinaryName(element).toString();
	}

	@Override
	@NotNull
	public final TypeElement typeElementFor(@NotNull final Class<?> clazz)
	{
		final String canonicalName = clazz.getCanonicalName();
		@Nullable final TypeElement typeElement = elementUtilities.getTypeElement(canonicalName);
		if (typeElement == null)
		{
			throw new IllegalArgumentException(format("No known TypeElement for clazz '%1$s'; is the --additional-classpath argument specified correctly?", canonicalName));
		}
		return typeElement;
	}

	@Override
	@NotNull
	public final WildcardType wildcardTypeSuper(@NotNull final TypeMirror superBound)
	{
		return typeUtilities.getWildcardType(null, superBound);
	}

	@Override
	@NotNull
	public final WildcardType wildcardTypeExtends(@NotNull final TypeMirror extendsBound)
	{
		return typeUtilities.getWildcardType(extendsBound, null);
	}

	// null for PackageElement
	@Override
	@Nullable
	public final TreePath treePathForElement(@NotNull final Element element)
	{
		return trees.getPath(element);
	}

	@Override
	public final boolean isEqualRawTypeElement(@SuppressWarnings("TypeMayBeWeakened") @NotNull final DeclaredType classInterfaceEnumOrAnnotation, @NotNull final Class<?> clazz)
	{
		final TypeElement typeElement = (TypeElement) classInterfaceEnumOrAnnotation.asElement();
		return typeElement.equals(typeElementFor(clazz));
		// return typeUtilities.isSameType(classInterfaceEnumOrAnnotation, typeFor(clazz));
	}

	private boolean hasAnnotationDirectlyOrInherited(@NotNull final Element element, @NotNull final Class<? extends Annotation> annotationClass)
	{
		final List<? extends AnnotationMirror> allAnnotationMirrors = elementUtilities.getAllAnnotationMirrors(element);
		for (final AnnotationMirror annotationMirror : allAnnotationMirrors)
		{
			if (isEqualRawTypeElement(annotationMirror.getAnnotationType(), annotationClass))
			{
				return true;
			}
		}
		return false;
	}
}

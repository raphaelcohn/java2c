package com.compilerUser.elementHandlers;

import com.sun.source.util.TreePath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;

public interface AbstractSyntaxTreeInterpreter
{
	@NotNull
	String getCanonicalClassName(@NotNull final TypeElement element);

	boolean isEqualRawTypeElement(@SuppressWarnings("TypeMayBeWeakened") @NotNull DeclaredType classInterfaceEnumOrAnnotation, @NotNull Class<?> clazz);

	@NotNull
	TypeElement typeElementFor(@NotNull Class<?> clazz);

	@NotNull
	WildcardType wildcardTypeSuper(@NotNull TypeMirror superBound);

	@NotNull
	WildcardType wildcardTypeExtends(@NotNull TypeMirror extendsBound);

	// null for PackageElement
	@Nullable
	TreePath treePathForElement(@NotNull Element element);
}

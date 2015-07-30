/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler;

import com.compilerUser.elementHandlers.AbstractAbstractSyntaxTreeInterpreter;
import com.compilerUser.elementHandlers.AbstractSyntaxTreeInterpreter;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.AbstractCType;
import com.java2c.model.types.Banished;
import com.java2c.model.types.Uncastable;
import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.transpiler.elementConverters.CType;
import com.compilerUser.elementHandlers.typeElementHandlers.ModifierValidator;
import com.compilerUser.exceptions.IncorrectSourceCodeException;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import static com.compilerUser.text.EnglishFormatter.format;
import static javax.lang.model.element.Modifier.*;
import static javax.lang.model.type.TypeKind.DECLARED;
import static javax.lang.model.type.TypeKind.ERROR;
import static javax.lang.model.type.TypeKind.NONE;

public final class OurAbstractSyntaxTreeInterpreter extends AbstractAbstractSyntaxTreeInterpreter
{
	@NotNull
	@NonNls
	private static final String JavaLangObject = "java.lang.Object";

	@NotNull
	private static final UnannotatedBanishedMethods AlsoBanished = new UnannotatedBanishedMethods()
	{{
		banish(JavaLangObject, "getClass");
		banish(JavaLangObject, "notify");
		banish(JavaLangObject, "notifyAll");
		banish(JavaLangObject, "wait");
	}};
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
	private final DeclaredType objectTypeMirror;

	@NotNull
	private final DeclaredType cTypeTypeMirror;

	@NotNull
	private final DeclaredType abstractCTypeTypeMirror;

	@NotNull
	private final DeclaredType abstractScalarTypeMirror;

	public OurAbstractSyntaxTreeInterpreter(@NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees)
	{
		super(typeUtilities, elementUtilities, trees);

		objectTypeMirror = (DeclaredType) typeElementFor(Object.class).asType();
		cTypeTypeMirror = (DeclaredType) typeElementFor(CType.class).asType();
		abstractCTypeTypeMirror = (DeclaredType) typeElementFor(AbstractCType.class).asType();
		abstractScalarTypeMirror = (DeclaredType) typeElementFor(AbstractScalar.class).asType();
	}

	@NotNull
	private static final ModifierValidator TypeModifierValidator = new ModifierValidator(NATIVE, STRICTFP, SYNCHRONIZED, TRANSIENT, VOLATILE);

	public void guardClassInheritsFromAbstractCType(@NotNull final TypeElement element)
	{
		TypeModifierValidator.validate(this, element);
		guardChainInheritsFromCType(element, abstractCTypeTypeMirror, false);
	}

	public void guardInterfaceInheritsFromCType(@NotNull final TypeElement element)
	{
		TypeModifierValidator.validate(this, element);
		guardChainInheritsFromCType(element, cTypeTypeMirror, true);
	}

	private void guardChainInheritsFromCType(@NotNull final TypeElement element, @SuppressWarnings("TypeMayBeWeakened") @NotNull final DeclaredType inheritsFrom, final boolean isInterface)
	{
		TypeElement currentTypeElement = element;
		DeclaredType currentDeclaredType = guardIsClassInterfaceEnumOrAnnotation(element.asType());
		do
		{
			if (typeUtilities.isSameType(objectTypeMirror, currentDeclaredType))
			{
				throw new IncorrectSourceCodeException(format("TypeElement '%1$s' must derive from AbstractCType", getCanonicalClassName(element)));
			}

			if (typeUtilities.isSameType(inheritsFrom, currentDeclaredType))
			{
				return;
			}

			for (final DeclaredType interfaceDeclaredType : interfaces(currentTypeElement))
			{
				guardInterfaceInheritsFromCType((TypeElement) interfaceDeclaredType.asElement());
			}

			if (isInterface)
			{
				return;
			}

			final TypeMirror superclass = currentTypeElement.getSuperclass();
			if (superclass.getKind() == NONE || superclass instanceof NoType)
			{
				throw new IncorrectSourceCodeException(format("TypeElement '%1$s' is a C class and so must derive from an abstract class (AbstractCType) not an interface", getCanonicalClassName(element)));
			}

			currentDeclaredType = guardIsClassInterfaceEnumOrAnnotation(superclass);
			currentTypeElement = (TypeElement) currentDeclaredType.asElement();

		} while(true);
	}

	public boolean isSuperclassAbstractScalar(@NotNull final TypeElement element)
	{
		final DeclaredType superclass = guardIsClassInterfaceEnumOrAnnotation(element.getSuperclass());
		return typeUtilities.isSameType(abstractScalarTypeMirror, superclass);
	}

	@NotNull
	public static List<DeclaredType> interfaces(@NotNull final TypeElement element)
	{
		final List<? extends TypeMirror> potentialInterfaces = element.getInterfaces();
		final List<DeclaredType> interfaces = new ArrayList<>(potentialInterfaces.size());
		for (final TypeMirror potentialInterface : potentialInterfaces)
		{
			interfaces.add(guardIsClassInterfaceEnumOrAnnotation( potentialInterface));
		}
		return interfaces;
	}

	@NotNull
	private static DeclaredType guardIsClassInterfaceEnumOrAnnotation(@NotNull final TypeMirror potentialClassInterfaceEnumOrAnnotation)
	{
		final TypeKind typeKind = potentialClassInterfaceEnumOrAnnotation.getKind();
		final String description = potentialClassInterfaceEnumOrAnnotation.toString();
		if (typeKind != DECLARED || !(potentialClassInterfaceEnumOrAnnotation instanceof DeclaredType))
		{
			// ErrorType and ErrorKind.ERROR should be set on the same class, but the API is ambiguous
			if (typeKind == ERROR || potentialClassInterfaceEnumOrAnnotation instanceof ErrorType)
			{
				throw new IncorrectSourceCodeException(format("Badly defined DeclaredType '%1$s'", description));
			}
			throw new IncorrectSourceCodeException(format("'%1$s' Was not TypeKind DECLARED, but '%2$s'", description, typeKind.name()));
		}

		return (DeclaredType) potentialClassInterfaceEnumOrAnnotation;
	}

	public void guardHasCCodeTemplateAndIsBanishedAreMutuallyExclusive(@SuppressWarnings("TypeMayBeWeakened") @NotNull final ExecutableElement executableElement)
	{
		if (hasCCodeTemplate(executableElement) && isBanished(executableElement))
		{
			@Nullable final TypeElement enclosingElement = (TypeElement) executableElement.getEnclosingElement();
			assert enclosingElement != null;
			throw new IncorrectSourceCodeException(format("ExecutableElement in '%1$s' has both @Banished and @CCodeTemplate", getCanonicalClassName(enclosingElement)));
		}
	}

	public boolean isBanished(@SuppressWarnings("TypeMayBeWeakened") @NotNull final ExecutableElement executableElement)
	{
		return hasAnnotationDirectlyOrInherited(executableElement, Banished.class) || AlsoBanished.isBanished(executableElement);
	}

	public boolean hasCCodeTemplate(@SuppressWarnings("TypeMayBeWeakened") @NotNull final ExecutableElement typeElement)
	{
		return hasAnnotationDirectlyOrInherited(typeElement, CCodeTemplate.class);
	}

	public boolean isUncastable(@SuppressWarnings("TypeMayBeWeakened") @NotNull final TypeElement typeElement)
	{
		return hasAnnotationDirectlyOrInherited(typeElement, Uncastable.class) || hasAnnotationDirectlyOrInherited(typeElement, Scalar.class);
	}

}

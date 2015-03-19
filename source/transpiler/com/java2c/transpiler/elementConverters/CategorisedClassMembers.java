package com.java2c.transpiler.elementConverters;

import com.java2c.transpiler.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.java2c.transpiler.elementConverters.FieldAttributesProcessors.StaticFieldAttributesProcessors;
import static java.lang.String.format;
import static java.util.Locale.ENGLISH;
import static javax.lang.model.element.Modifier.*;

public final class CategorisedClassMembers
{
	@NotNull private final TypeElement clazz;
	@NotNull private final Name className;
	@NotNull private final String classNameEscaped;
	@NotNull private final List<VariableElement> staticFields;
	@NotNull private final List<ExecutableElement> staticInitializers;
	@NotNull private final List<VariableElement> instanceFields;
	@NotNull private final List<ExecutableElement> instanceInitializers;
	@NotNull private final List<ExecutableElement> constructors;
	@NotNull private final List<ExecutableElement> staticMethods;
	@NotNull private final List<ExecutableElement> instanceMethods;

	public CategorisedClassMembers(@NotNull final TypeElement clazz, @NotNull final Name className) throws ConversionException
	{
		this.clazz = clazz;
		this.className = className;
		classNameEscaped = escapeJavaTypeName(className);
		staticFields = new ArrayList<>(16);
		staticInitializers = new ArrayList<>(16);
		instanceFields = new ArrayList<>(16);
		instanceInitializers = new ArrayList<>(16);
		constructors = new ArrayList<>(16);
		staticMethods = new ArrayList<>(16);
		instanceMethods = new ArrayList<>(16);

		@NotNull final List<? extends Element> enclosedElements = clazz.getEnclosedElements();
		for (final Element enclosedElement : enclosedElements)
		{
			@NotNull final ElementKind kind = enclosedElement.getKind();
			final Set<Modifier> modifiers = enclosedElement.getModifiers();
			switch(kind)
			{
				case STATIC_INIT:
					staticInitializers.add((ExecutableElement) enclosedElement);
					break;

				case INSTANCE_INIT:
					instanceInitializers.add((ExecutableElement) enclosedElement);
					break;

				case PACKAGE:
					break;
				case ENUM:
					break;
				case CLASS:
					break;
				case ANNOTATION_TYPE:
					break;
				case INTERFACE:
					break;
				case ENUM_CONSTANT:
					break;
				case FIELD:
					final List<VariableElement> fields = modifiers.contains(STATIC) ? staticFields : instanceFields;
					fields.add((VariableElement) enclosedElement);
					break;

				case CONSTRUCTOR:
					constructors.add((ExecutableElement) enclosedElement);
					break;

				case PARAMETER:
					break;
				case LOCAL_VARIABLE:
					break;
				case EXCEPTION_PARAMETER:
					break;
				case METHOD:
					final List<ExecutableElement> methods = modifiers.contains(STATIC) ? staticMethods : instanceMethods;
					methods.add((ExecutableElement) enclosedElement);
					break;

				case TYPE_PARAMETER:
					break;
				case OTHER:
					break;
				case RESOURCE_VARIABLE:
					break;
				default:
					throw new ConversionException(format(ENGLISH, "Unexpected class member kind %1$s", kind));
			}
		}
	}

	public void processStaticFields() throws ConversionException
	{
		// Explanation of volatile's uselessness: http://stackoverflow.com/questions/2484980/why-is-volatile-not-considered-useful-in-multithreaded-c-or-c-programming#2485177

		/*
			In Java:-
				http://javamex.com/tutorials/synchronization_volatile.shtml

			So, if we convert a volatile variable into C-land, we use the volatile variable and all accesses need a memory barrier!
		 */

		/*
			http://stackoverflow.com/questions/572547/what-does-static-mean-in-a-c-program/572550#572550

			So, for static fields, the following rules apply

				For public static final & literal value, we can do
					header: extern const int fieldName;
					source: const int fieldName = LITERAL;

				For public static final & non-literal value, we can do:
					header: extern int fieldName;
					source: int fieldName;
					void static()
					{
						any_other_static_init1();
						fieldName = NON-LITERAL
						any_other_static_init2();
					}

				For public static & literal value, we can do
					header: extern int fieldName;
					source: int fieldName = LITERAL;

				(Same as that above)
				For public static & non-literal value, we can do:
					header: extern int fieldName;
					source: int fieldName;
					void static()
					{
						any_other_static_init1();
						fieldName = NON-LITERAL
						any_other_static_init2();
					}

				Ditto for protected and none

				For private, there is (a) no extern and (b) all variables are declared with storage class STATIC

		 */
		for (VariableElement staticField : staticFields)
		{
			processStaticField(staticField);
		}
	}

	@NotNull
	public static String escapeJavaTypeName(@NotNull final Name className)
	{
		return escapeJavaTypeName(className.toString());
	}

	@NotNull
	public static String escapeJavaTypeName(@NotNull final String className)
	{
		return className.replace('.', '_');
	}

	@NotNull
	public static String escapeFieldName(@NotNull final String classNameEscaped, @NotNull final VariableElement staticField)
	{
		return classNameEscaped + '_' + staticField.getSimpleName().toString();
	}

	private void processStaticField(@NotNull final VariableElement staticField) throws ConversionException
	{
		//final CType cType = typeResolver.resolveType(staticField.asType());

		final String fieldName = escapeFieldName(classNameEscaped, staticField);

		final List<GccAttribute<GccVariableAttributeName>> gccAttributes = StaticFieldAttributesProcessors.processFieldAttributes(staticField);

		// TODO: __thread specification, type, literal conversion


		//new VariableDeclaration(storageClass, isConst, isVolatile, typeName, gccAttributes);


		final Set<Modifier> modifiers = staticField.getModifiers();
		final boolean isVisible = !modifiers.contains(PRIVATE); // public, protected and package-visible are all visible
		final boolean isFinal = modifiers.contains(FINAL);
		@Nullable final Object literalValue = staticField.getConstantValue();
		final boolean hasLiteralValue = literalValue != null;

		@Nullable final String headerDeclaration;
		final String sourceDeclaration;
		if (isVisible)
		{
			if (isFinal)
			{
				if (hasLiteralValue)
				{
					// TODO: const needs to shift position for a pointer argument to the RIGHT of the asterisk
					headerDeclaration = "extern const CLASSNAME fieldName;";
					sourceDeclaration = "const CLASSNAME fieldName = LITERAL";
				}
				else
				{
					headerDeclaration = "extern CLASSNAME fieldName;";
					sourceDeclaration = "CLASSNAME fieldName;";
					// add a pre-amble to the static initializers to NULL out / default all fields not defined as const
					// add fieldName = NON-LITERAL; to the static initializers IN ORDER
				}
			}
			else
			{
				if (hasLiteralValue)
				{
					headerDeclaration = "extern CLASSNAME fieldName;";
					sourceDeclaration = "CLASSNAME fieldName = LITERAL";
				}
				else
				{
					headerDeclaration = "extern CLASSNAME fieldName;";
					sourceDeclaration = "CLASSNAME fieldName;";
					// add a pre-amble to the static initializers to NULL out / default all fields not defined as const
					// add fieldName = NON-LITERAL; to the static initializers IN ORDER
				}
			}
		}
		else
		{
			headerDeclaration = null;
			if (isFinal)
			{
				if (hasLiteralValue)
				{
					// TODO: If we have attributes, we'll need a second definition
					sourceDeclaration = "static const CLASSNAME fieldName = LITERAL";
				}
				else
				{
					// TODO: If we have attributes, we'll need to not output the semi-colon ;
					sourceDeclaration = "static CLASSNAME fieldName;";
					// add a pre-amble to the static initializers to NULL out / default all fields not defined as const
					// add fieldName = NON-LITERAL; to the static initializers IN ORDER
				}
			}
			else
			{
				if (hasLiteralValue)
				{
					sourceDeclaration = "static CLASSNAME fieldName = LITERAL";
				}
				else
				{
					sourceDeclaration = "static CLASSNAME fieldName;";
					// add a pre-amble to the static initializers to NULL out / default all fields not defined as const
					// add fieldName = NON-LITERAL; to the static initializers IN ORDER
				}
			}
		}
	}
}

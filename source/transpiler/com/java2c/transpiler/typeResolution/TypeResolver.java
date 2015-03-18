package com.java2c.transpiler.typeResolution;

import com.java2c.model.pointer;
import com.java2c.transpiler.elementConverters.CType;
import com.java2c.transpiler.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.List;

import static com.java2c.utility.EnglishFormatter.format;

public final class TypeResolver
{
	@NotNull private final TypeHelper typeHelper;
	@NotNull private final TypeMirror pointerTypeElementType;

	public TypeResolver(@NotNull final TypeHelper typeHelper)
	{
		this.typeHelper = typeHelper;
		pointerTypeElementType = typeHelper.typeFor(pointer.class);
	}

	@NotNull
	public CType resolveType(@NotNull final TypeMirror fieldType) throws ConversionException
	{
		final TypeKind typeKind = fieldType.getKind();
		guardType(typeKind);
		if (typeKind.isPrimitive())
		{
			throw new ConversionException("Fix me for primitives");
		}


		final String typeName = fieldType.toString();

		@Nullable final TypeElement fieldTypeElement = typeHelper.typeElementFor(fieldType);
		if (fieldTypeElement == null)
		{
			throw new ConversionException(format("Missing type information for %1$s (check classpath and java source)", typeName));
		}

		final List<? extends TypeParameterElement> typeParameters = fieldTypeElement.getTypeParameters();

//		if (typeHelper.isSubtype(fieldType, primitiveTypeElementType))
//		{
//			return resolvePrimitiveType(typeName, fieldTypeElement, typeParameters);
//		}

		final TypeMirror fieldTypeWithoutParameters = fieldTypeElement.asType();
		// TypeSymbol  && typeParameters.size() == 1 && typeParameters.get(0).equal(TypeSymbol("P"))
		if (typeHelper.isSubtype(fieldTypeWithoutParameters, pointerTypeElementType))
		{
			// WildcardType ?, ? extends SSS, ? super ZZZ
			// ClassType eg uint8
			// Also could be a pointer, eg pointer<pointer<?>> for **void
			final List<? extends TypeMirror> typeArguments = ((DeclaredType) fieldType).getTypeArguments();
			if (typeArguments.size() != 1 || typeParameters.size() != 1)
			{
				throw new ConversionException(format("Subtypes of pointer must preserve exactly one generic argument for type %1$s", typeName));
			}

			// ?
			final TypeMirror typeMirror = typeArguments.get(0);
			if (typeMirror.equals(typeHelper.wildcardTypeAny))
			{
				// void pointer
			}

			throw new IllegalStateException();
		}

		// TODO: pointer types (are generic, yuck)




		throw new IllegalStateException();
	}

	@SuppressWarnings("OverlyComplexMethod")
	private static void guardType(final TypeKind typeKind) throws ConversionException
	{
		switch (typeKind)
		{
			case BOOLEAN:
				break;
			case BYTE:
				break;
			case SHORT:
				break;
			case INT:
				break;
			case LONG:
				break;
			case CHAR:
				break;
			case FLOAT:
				break;
			case DOUBLE:
				break;
			case ARRAY:
				throw new ConversionException("Arrays are not yet supported");
			case DECLARED:
				break;
			case ERROR:
				throw new ConversionException("Unresolvable type (implies compilation failure)");
			case TYPEVAR:
				//noinspection fallthrough
			case WILDCARD:
				//noinspection fallthrough
			case VOID:
				//noinspection fallthrough
			case NONE:
				//noinspection fallthrough
			case NULL:
				//noinspection fallthrough
			case PACKAGE:
				//noinspection fallthrough
			case EXECUTABLE:
				//noinspection fallthrough
			case OTHER:
				throw new ConversionException("Type should not be possible in context");
			case UNION:
				throw new ConversionException("Java 7 Union types are not supported");
		}
	}

//	private static CType resolvePrimitiveType(final String typeName, final TypeElement fieldTypeElement, final Collection<? extends TypeParameterElement> typeParameters) throws ConversionException
//	{
//		if (!typeParameters.isEmpty())
//		{
//			throw new ConversionException(format("Type %1$s has type parameters", typeName));
//		}
//
//		@Nullable final PrimitiveConversion primitiveConversion = fieldTypeElement.getAnnotation(PrimitiveConversion.class);
//		if (primitiveConversion == null)
//		{
//			throw new ConversionException(format("Missing @PrimitiveConversion annotation for %1$s", typeName));
//		}
//
//		@Nullable final String cTypeName = primitiveConversion.value();
//		// Technically, we may be given code that doesn't respect this annotation
//		//noinspection ConstantConditions
//		if (cTypeName == null)
//		{
//			throw new ConversionException(format("Null value field on @PrimitiveConversion for type %1$s", typeName));
//		}
//
//		@Nullable final String includeWithAngleBracketsOrDoubleQuotes;
//		if (primitiveConversion.typedef())
//		{
//			includeWithAngleBracketsOrDoubleQuotes = '"' + escapeJavaTypeName(typeName) + '"';
//		}
//		else
//		{
//			@Nullable final String systemHeader = primitiveConversion.systemHeader();
//			// Technically, we may be given code that doesn't respect this annotation
//			//noinspection ConstantConditions
//			if (systemHeader == null)
//			{
//				throw new ConversionException(format("Null systemHeader field on @PrimitiveConversion for type %1$s", typeName));
//			}
//			includeWithAngleBracketsOrDoubleQuotes = systemHeader.isEmpty() ? null : '<' + systemHeader + '>';
//		}
//
//		return new CType(cTypeName, includeWithAngleBracketsOrDoubleQuotes);
//	}
}

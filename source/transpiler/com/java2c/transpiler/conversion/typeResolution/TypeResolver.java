package com.java2c.transpiler.conversion.typeResolution;

import com.java2c.model.Primitive;
import com.java2c.model.pointer;
import com.java2c.model.primitives.PrimitiveConversion;
import com.java2c.transpiler.conversion.elementConverters.CType;
import com.java2c.transpiler.conversion.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.Collection;
import java.util.List;

import static com.java2c.transpiler.conversion.elementConverters.CategorisedClassMembers.escapeJavaTypeName;
import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class TypeResolver
{
	@NotNull private final TypeHelper typeHelper;
	@NotNull private final TypeMirror primitiveTypeElementType;
	@NotNull private final TypeMirror pointerTypeElementType;

	/*
		Other types to support fundamentally:-
			stdint.h
			intptr_t    typedef long   intptr_t;
			uintptr_t   typedef unsigned long   uintptr_t;
			intmax_t    overridable, might be typedef long long                intmax_t;
			uintmax_t   overridable, might be typedef unsigned long long                uintmax_t;
			int_leastXXXX_t; 8,16,32,64
			uint_leastXXXX_t; 8,16,32,64
			int_fastXXXX_t; 8,16,32,64
			uint_fastXXXX_t; 8,16,32,64


			stddef.h
			ptrdiff_t   // difference of two pointers
			size_t      // size of an int, struct, etc. We're probably not going to implement a true sizeof
			wchar_t
			wint_t    // wide enough for wchar_t and WEOF (ie -1 for wide char functions)  see  https://www.gnu.org/software/libc/manual/html_node/Extended-Char-Intro.html
			NULL - stddef.h or string.h


			(float.h)
			float_t
			double_t
	 */

	public TypeResolver(@NotNull final TypeHelper typeHelper)
	{
		this.typeHelper = typeHelper;
		primitiveTypeElementType = typeHelper.typeFor(Primitive.class);
		pointerTypeElementType = typeHelper.typeFor(pointer.class);

//		addJava("boolean", "_Bool");
//		addJava("byte", "int8_t");
//		addJava("short", "int16_t");
//		addJava("int", "int32_t");
//		addJava("long", "int64_t");
//		addJava("char", "uint16_t");
//		addJava("float", "float");
//		addJava("double", "double");
//		addJavaPointer(Boolean.class, "_Bool");
//		addJavaPointer(Byte.class, "int8_t");
//		addJavaPointer(Short.class, "int16_t");
//		addJavaPointer(Integer.class, "int32_t");
//		addJavaPointer(Long.class, "int64_t");
//		addJavaPointer(Character.class, "uint16_t");
//		addJavaPointer(Float.class, "float");
//		addJavaPointer(Double.class, "double");
//		addPrimitive(char_.class, "char");
//		addPrimitive(signed_char.class, "signed char");
//		addPrimitive(signed_short.class, "signed short");
//		addPrimitive(signed_int.class, "signed int");
//		addPrimitive(signed_long.class, "signed long");
//		addPrimitive(signed_long_long.class, "signed long long");
//		addPrimitive(unsigned_char.class, "unsigned char");
//		addPrimitive(unsigned_short.class, "unsigned short");
//		addPrimitive(unsigned_int.class, "unsigned int");
//		addPrimitive(unsigned_long.class, "unsigned long");
//		addPrimitive(unsigned_long_long.class, "unsigned long long");
//		addPrimitive(double_.class, "double"); // review necessitity
//		addPrimitive(float_.class, "float"); // review necessitity
//		addPrimitive(long_double.class, "long double");

		// TODO: Pointers, wrapper types for java types (we can use boxed java types)
		// void pointer is represented by pointer<?>
		// TODO: float_t, double_t [has a dependency on float.h]
		// TODO: size_t, off
		// TODO: Extending primitive types - we should allow a replacement type definition, let it extend Primitive and define itself

		// WE HAVE DITCHED @unsigned - whilst lightweight, for now it means every line of code needed to be annotated, which is pretty unlikely for an IDE
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
			throw new ConversionException(format(ENGLISH, "Missing type information for %1$s (check classpath and java source)", typeName));
		}

		final List<? extends TypeParameterElement> typeParameters = fieldTypeElement.getTypeParameters();

		if (typeHelper.isSubtype(fieldType, primitiveTypeElementType))
		{
			return resolvePrimitiveType(typeName, fieldTypeElement, typeParameters);
		}

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
				throw new ConversionException(format(ENGLISH, "Subtypes of pointer must preserve exactly one generic argument for type %1$s", typeName));
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

	private static CType resolvePrimitiveType(final String typeName, final TypeElement fieldTypeElement, final Collection<? extends TypeParameterElement> typeParameters) throws ConversionException
	{
		if (!typeParameters.isEmpty())
		{
			throw new ConversionException(format(ENGLISH, "Type %1$s has type parameters", typeName));
		}

		@Nullable final PrimitiveConversion primitiveConversion = fieldTypeElement.getAnnotation(PrimitiveConversion.class);
		if (primitiveConversion == null)
		{
			throw new ConversionException(format(ENGLISH, "Missing @PrimitiveConversion annotation for %1$s", typeName));
		}

		@Nullable final String cTypeName = primitiveConversion.value();
		// Technically, we may be given code that doesn't respect this annotation
		//noinspection ConstantConditions
		if (cTypeName == null)
		{
			throw new ConversionException(format(ENGLISH, "Null value field on @PrimitiveConversion for type %1$s", typeName));
		}

		@Nullable final String includeWithAngleBracketsOrDoubleQuotes;
		if (primitiveConversion.typedef())
		{
			includeWithAngleBracketsOrDoubleQuotes = '"' + escapeJavaTypeName(typeName) + '"';
		}
		else
		{
			@Nullable final String systemHeader = primitiveConversion.systemHeader();
			// Technically, we may be given code that doesn't respect this annotation
			//noinspection ConstantConditions
			if (systemHeader == null)
			{
				throw new ConversionException(format(ENGLISH, "Null systemHeader field on @PrimitiveConversion for type %1$s", typeName));
			}
			includeWithAngleBracketsOrDoubleQuotes = systemHeader.isEmpty() ? null : '<' + systemHeader + '>';
		}

		return new CType(cTypeName, includeWithAngleBracketsOrDoubleQuotes);
	}
}

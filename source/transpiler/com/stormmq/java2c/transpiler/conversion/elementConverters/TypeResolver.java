package com.stormmq.java2c.transpiler.conversion.elementConverters;

import com.stormmq.java2c.model.primitives.*;
import com.stormmq.java2c.model.variables.unsigned;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class TypeResolver
{
	@NotNull private final Map<String, String> builtInTypes;
	@NotNull private final Map<String, String> builtInUnsignedTypes;

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

	public TypeResolver()
	{
		builtInTypes = new HashMap<>();
		builtInUnsignedTypes = new HashMap<>();

		addJava("boolean", "_Bool");
		addJava("byte", "int8_t");
		addJava("short", "int16_t");
		addJava("int", "int32_t");
		addJava("long", "int64_t");
		addJava("char", "uint16_t");
		addJava("float", "float");
		addJava("double", "double");

		addJavaPointer(Boolean.class, "_Bool");
		addJavaPointer(Byte.class, "int8_t");
		addJavaPointer(Short.class, "int16_t");
		addJavaPointer(Integer.class, "int32_t");
		addJavaPointer(Long.class, "int64_t");
		addJavaPointer(Character.class, "uint16_t");
		addJavaPointer(Float.class, "float");
		addJavaPointer(Double.class, "double");

		xxxx;
		// populate dynamically, if possible, by looking for @PrimitiveConversion / implements Primitive


		addPrimitive(char_.class, "char");
		addPrimitive(signed_char.class, "signed char");
		addPrimitive(signed_short.class, "signed short");
		addPrimitive(signed_int.class, "signed int");
		addPrimitive(signed_long.class, "signed long");
		addPrimitive(signed_long_long.class, "signed long long");
		addPrimitive(unsigned_char.class, "unsigned char");
		addPrimitive(unsigned_short.class, "unsigned short");
		addPrimitive(unsigned_int.class, "unsigned int");
		addPrimitive(unsigned_long.class, "unsigned long");
		addPrimitive(unsigned_long_long.class, "unsigned long long");
		addPrimitive(double_.class, "double"); // review necessitity
		addPrimitive(float_.class, "float"); // review necessitity
		addPrimitive(long_double.class, "long double");

		addJavaUnsigned("byte", "uint8_t");
		addJavaUnsigned("int", "uint32_t");
		addJavaUnsigned("long", "uint64_t");

		addJavaUnsignedPointer(Byte.class, "uint8_t");
		addJavaUnsignedPointer(Integer.class, "uint32_t");
		addJavaUnsignedPointer(Long.class, "uint64_t");

		// TODO: Pointers, wrapper types for java types (we can use boxed java types)
		// void pointer is represented by pointer<?>
		// TODO: float_t, double_t [has a dependency on float.h]
		// TODO: size_t, off
		// TODO: Extending primitive types - we should allow a replacement type definition, let it extend Primitive and define itself
	}

	@NotNull
	public String cType(@NotNull final VariableElement field)
	{
		final String typeName = field.asType().toString();
		final boolean isUnsigned = hasAnnotation(field, unsigned.class);
		if (isUnsigned)
		{
			@Nullable final String cType = builtInUnsignedTypes.get(typeName);
			if (cType == null)
			{
				throw new ConversionException(format(ENGLISH, "@unsigned is not appropriate for type %1$s", typeName));
			}
			return cType;
		}
	}

	@NotNull
	public String resolveType(@NotNull final VariableElement field) throws ConversionException
	{
		final String typeName = field.asType().toString();
		final boolean isUnsigned = hasAnnotation(field, unsigned.class);
		if (isUnsigned)
		{
			@Nullable final String cType = builtInUnsignedTypes.get(typeName);
			if (cType == null)
			{
				throw new ConversionException(format(ENGLISH, "@unsigned is not appropriate for type %1$s", typeName));
			}
			return cType;
		}

		if (typeName.equals(c_void.class.getCanonicalName()))
		{
			throw new ConversionException(format(ENGLISH, "c_void is not appropriate for a non-pointer value"));
		}

		@Nullable final String builtInType = builtInTypes.get(typeName);
	}

	private void addJava(@NotNull final String javaType, @NotNull final String cType)
	{
		builtInTypes.put(javaType, cType);
	}

	private void addJavaPointer(@NotNull final Class<?> javaType, @NotNull final String cType)
	{
		builtInTypes.put(javaType.getCanonicalName(), cType);
	}

	private void addJavaUnsigned(@NotNull final String javaType, @NotNull final String cType)
	{
		builtInUnsignedTypes.put(javaType, cType);
	}

	private void addJavaUnsignedPointer(@NotNull final Class<?> javaType, @NotNull final String cType)
	{
		builtInUnsignedTypes.put(javaType.getCanonicalName(), cType + " *");
	}

	private void addPrimitive(@NotNull final Class<?> primitiveShortNameType, @NotNull final String cType)
	{
		builtInTypes.put(primitiveShortNameType.getCanonicalName(), cType);
	}

	private static <A extends Annotation> boolean hasAnnotation(@NotNull final Element element, @NotNull final Class<A> annotation)
	{
		return element.getAnnotation(annotation) != null;
	}
}

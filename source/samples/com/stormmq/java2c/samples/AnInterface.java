package com.stormmq.java2c.samples;

import com.stormmq.java2c.model.primitives.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.stormmq.java2c.model.primitives.double_.double_;
import static com.stormmq.java2c.model.primitives.double_t.double_t;
import static com.stormmq.java2c.model.primitives.float_.float_;
import static com.stormmq.java2c.model.primitives.float_t.float_t;
import static com.stormmq.java2c.model.primitives.uint32.uint32;
import static com.stormmq.java2c.model.primitives.uint64.uint64;
import static com.stormmq.java2c.model.primitives.uint8.uint8;

@SuppressWarnings({"InterfaceNeverImplemented", "UnusedDeclaration"})
public interface AnInterface
{
	/*

		addJava("boolean", "_Bool");
		addJava("byte", "int8_t");
		addJava("short", "int16_t");
		addJava("int", "int32_t");
		addJava("long", "int64_t");
		addJava("char", "uint16_t");
		addJava("float", "float");
		addJava("double", "double");
	 */


	@SuppressWarnings("ConstantNamingConvention") @Nullable pointer<?> pointerVoid = null;
	@SuppressWarnings("ConstantNamingConvention") @Nullable pointer<uint8> pointerUint8 = null;
	@SuppressWarnings("ConstantNamingConvention") @Nullable pointer<? super uint8> pointerVoidB = null;
	@SuppressWarnings("ConstantNamingConvention") @Nullable pointer<? extends uint8> pointerVoidA = null;
	@SuppressWarnings("ConstantNamingConvention") @Nullable pointer<pointer<?>> pointerPointerVoid = null;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdbool.h>
			extern const _Bool com_stormmq_java2c_samples2_AnInterface_boolLiteral;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdbool.h>
			const _Bool com_stormmq_java2c_samples2_AnInterface_boolLiteral = true;
	 */
	@SuppressWarnings("ConstantNamingConvention") boolean boolLiteral = true;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int8_t com_stormmq_java2c_samples2_AnInterface_int8Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int8_t com_stormmq_java2c_samples2_AnInterface_int8Literal = INT8_C(10);
	 */
	@SuppressWarnings("ConstantNamingConvention") byte int8Literal = (byte) 10; // TODO: test non-cast and see if that's also literal

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint8_t com_stormmq_java2c_samples2_AnInterface_uint8Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint8_t com_stormmq_java2c_samples2_AnInterface_uint8Literal = UINT8_C(11);
	 */
	@SuppressWarnings("ConstantNamingConvention") @NotNull uint8 uint8Literal = uint8((byte) 11);

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int16_t com_stormmq_java2c_samples2_AnInterface_int16Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int16_t com_stormmq_java2c_samples2_AnInterface_int16Literal = INT16_C(20);
	 */
	@SuppressWarnings("ConstantNamingConvention") short int16Literal = (short) 20;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint16_t com_stormmq_java2c_samples2_AnInterface_uint16Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint16_t com_stormmq_java2c_samples2_AnInterface_uint16Literal = UINT16_C(21);
	 */
	@SuppressWarnings("ConstantNamingConvention") char uint16Literal = (char) 21;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int32_t com_stormmq_java2c_samples2_AnInterface_int32Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int32_t com_stormmq_java2c_samples2_AnInterface_int32Literal = INT32_C(30);
	 */
	@SuppressWarnings("ConstantNamingConvention") int int32Literal = 30;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint32_t com_stormmq_java2c_samples2_AnInterface_uint32Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint32_t com_stormmq_java2c_samples2_AnInterface_uint32Literal = UINT32_C(31);
	 */
	@SuppressWarnings("ConstantNamingConvention") @NotNull uint32 uint32Literal = uint32(31);

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int64_t com_stormmq_java2c_samples2_AnInterface_int64Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int64_t com_stormmq_java2c_samples2_AnInterface_int64Literal = INT64_C(30);
	 */
	@SuppressWarnings("ConstantNamingConvention") long int64Literal = 30L;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint64_t com_stormmq_java2c_samples2_AnInterface_uint64Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint64_t com_stormmq_java2c_samples2_AnInterface_uint64Literal = UINT64_C(31);
	 */
	@SuppressWarnings("ConstantNamingConvention") @NotNull uint64 uint64Literal = uint64(31L);

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint64_t com_stormmq_java2c_samples2_AnInterface_floatLiteral;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint64_t com_stormmq_java2c_samples2_AnInterface_floatLiteral = 41.1f;
	 */
	@SuppressWarnings("ConstantNamingConvention") float floatLiteral = 41.1f;
	@SuppressWarnings("ConstantNamingConvention") @NotNull float_ float_Literal = float_(42.1f);
	@SuppressWarnings("ConstantNamingConvention") @NotNull float_t float_tLiteral = float_t(43.1f);

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint64_t com_stormmq_java2c_samples2_AnInterface_doubleLiteral;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint64_t com_stormmq_java2c_samples2_AnInterface_doubleLiteral = 51.1d;
	 */
	@SuppressWarnings("ConstantNamingConvention") double doubleLiteral = 51.1d;
	@SuppressWarnings("ConstantNamingConvention") @NotNull double_ double_Literal = double_(52.1d);
	@SuppressWarnings("ConstantNamingConvention") @NotNull double_t double_tLiteral = double_t(53.1d);

	// This type is a primitive
	// Simple-valued constructor is equivalent to literal value
	@SuppressWarnings("ConstantNamingConvention") @NotNull char_ charLiteral = new char_((byte) 10); // Will appear to be non-constant to java, but should be something we can sort out. Or not bother.

	@SuppressWarnings("ConstantNamingConvention") @NotNull long_double longDoubleLiteral = new long_double(10.5d); // Not sure on the suffix for a long double










	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdbool.h>
			extern _Bool com_stormmq_java2c_samples2_AnInterface_boolNonLiteral;
			extern void com_stormmq_java2c_samples2_AnInterface_static();

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdbool.h>
			const _Bool com_stormmq_java2c_samples2_AnInterface_boolNonLiteral;
			extern void com_stormmq_java2c_samples2_AnInterface_static()
			{
				...
				boolNonLiteral = booleanInitializer();
				...
			}
	 */
	// boolean boolNonLiteral = booleanInitializer();
}

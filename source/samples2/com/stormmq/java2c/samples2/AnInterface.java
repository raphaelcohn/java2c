package com.stormmq.java2c.samples2;

import com.stormmq.java2c.model.primitives.char_;
import com.stormmq.java2c.model.primitives.long_double;
import com.stormmq.java2c.model.variables.unsigned;
import org.jetbrains.annotations.NotNull;

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

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdbool.h>
			extern const _Bool com_stormmq_java2c_samples2_AnInterface_boolLiteral;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdbool.h>
			const _Bool com_stormmq_java2c_samples2_AnInterface_boolLiteral = true;
	 */
	boolean boolLiteral = true;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int8_t com_stormmq_java2c_samples2_AnInterface_int8Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int8_t com_stormmq_java2c_samples2_AnInterface_int8Literal = INT8_C(10);
	 */
	byte int8Literal = (byte) 10; // TODO: test non-cast and see if that's also literal

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint8_t com_stormmq_java2c_samples2_AnInterface_uint8Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint8_t com_stormmq_java2c_samples2_AnInterface_uint8Literal = UINT8_C(11);
	 */
	@unsigned byte uint8Literal = (byte) 11;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int16_t com_stormmq_java2c_samples2_AnInterface_int16Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int16_t com_stormmq_java2c_samples2_AnInterface_int16Literal = INT16_C(20);
	 */
	short int16Literal = (short) 20;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint16_t com_stormmq_java2c_samples2_AnInterface_uint16Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint16_t com_stormmq_java2c_samples2_AnInterface_uint16Literal = UINT16_C(21);
	 */
	char uint16Literal = (char) 21;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int32_t com_stormmq_java2c_samples2_AnInterface_int32Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int32_t com_stormmq_java2c_samples2_AnInterface_int32Literal = INT32_C(30);
	 */
	int int32Literal = 30;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint32_t com_stormmq_java2c_samples2_AnInterface_uint32Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint32_t com_stormmq_java2c_samples2_AnInterface_uint32Literal = UINT32_C(31);
	 */
	@unsigned int uint32Literal = 31;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const int64_t com_stormmq_java2c_samples2_AnInterface_int64Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const int64_t com_stormmq_java2c_samples2_AnInterface_int64Literal = INT64_C(30);
	 */
	long int64Literal = 30L;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint64_t com_stormmq_java2c_samples2_AnInterface_uint64Literal;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint64_t com_stormmq_java2c_samples2_AnInterface_uint64Literal = UINT64_C(31);
	 */
	@unsigned long uint64Literal = 31L;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint64_t com_stormmq_java2c_samples2_AnInterface_floatLiteral;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint64_t com_stormmq_java2c_samples2_AnInterface_floatLiteral = 41.1f;
	 */
	float floatLiteral = 41.1f;

	/*
		com_stormmq_java2c_samples2_AnInterface.h:
			#include <stdint.h>
			extern const uint64_t com_stormmq_java2c_samples2_AnInterface_doubleLiteral;

		com_stormmq_java2c_samples2_AnInterface.c:
			#include <stdint.h>
			const uint64_t com_stormmq_java2c_samples2_AnInterface_doubleLiteral = 51.1d;
	 */
	double doubleLiteral = 51.1d;

	// This type is a primitive
	// Simple-valued constructor is equivalent to literal value
	@NotNull char_ charLiteral = new char_((byte) 10); // Will appear to be non-constant to java, but should be something we can sort out. Or not bother.

	@NotNull long_double longDoubleLiteral = new long_double(10.5d); // Not sure on the suffix for a long double










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

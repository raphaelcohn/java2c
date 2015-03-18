package com.stormmq.java2c.model.primitives;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface PrimitiveConversion
{
	@NotNull String value();

	@NotNull String systemHeader() default "";

	// requires a typedef. Not likely most of the time
	boolean typedef() default false;

	boolean isUnsigned() default false;

	/*

		Scenarios:-
			- fundamental C types, like  unsigned long long  which may be represented as a class (unsigned_long_long) or a java primitive  (@unsigned long)
				- do not have any header imports
				- eg @PrimitiveConversion("signed char")

			- derived C types, but in the standard libraries, such as float_t and double_t, size_t, etc, which are usually typedef'd but in standard headers
				- eg @PrimitiveConversion(value = "float_t", systemHeader = "float.h")
					#include <float.h>
					...

			- derived C types, but not in the standard libraries, and we want to typedef and import
				- eg @PrimitiveConversion(value = "uint32_t", systemHeader = "stdint.h", typedef = true)
					class Package.MyClass
					#include <stdint.h>
					typedef uint32_t Package_MyClasss

					...

					#include "Package_MyClass.h"

					...

	 */
}

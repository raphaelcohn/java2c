package com.stormmq.java2c.model.variables;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface aligned
{
	/**
	 * Maps to GCC's __BIGGEST_ALIGNMENT__
 	 */
	int BiggestAlignment = -1;

	/**
	 * Alignments are in bytes, unless 0 (default) or BiggestAlignment is used
	 * @return 0 is default alignment; use a power of 2
	 */
	int value() default 0;
}

package com.java2c.model.types.scalars;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
    A Scalar is used to define that a class represents a scalar value, such as c_signed_char

    The value is the C type (eg 'signed char')

    A Scalar has a single constructor which takes a Java long. Thus

    c_signed_char x = new c_signed_char(100L)

        is transformed into

    signed char x = 100

    (with appropriate conversion of 100L)

    Scalars are implicitly @Uncastable
 */

@Target(TYPE)
@Retention(RUNTIME)
public @interface Scalar
{
	@NotNull String value();

	@NotNull String[] includes() default {};

	boolean signed() default false;

	boolean typedef() default false;
}

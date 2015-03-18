package com.java2c.samples4;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@SuppressWarnings("AnnotationNamingConvention")
@Target(TYPE)
@Retention(SOURCE)
public @interface Scalar
{
	@NotNull String value();

	@NotNull String[] includes() default {};

	boolean signed() default false;

	boolean typedef() default false;
}

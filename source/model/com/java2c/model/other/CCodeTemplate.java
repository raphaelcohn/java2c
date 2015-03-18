package com.java2c.model.other;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Target({CONSTRUCTOR, METHOD, FIELD})
@Retention(SOURCE)
public @interface CCodeTemplate
{
	@NotNull String[] value();

	@NotNull String[] includes() default {};

	@NotNull String Scalar = "(@value@)";
}

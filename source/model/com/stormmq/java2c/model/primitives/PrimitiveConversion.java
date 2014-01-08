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
}

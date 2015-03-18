package com.stormmq.java2c.model;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SuppressWarnings("AnnotationNamingConvention")

@Retention(RUNTIME)
@Target(METHOD)
public @interface CCode
{
	@NotNull String[] value();
}

package com.java2c.samples4;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@SuppressWarnings("AnnotationNamingConvention")
@Target(PARAMETER)
@Retention(SOURCE)
public @interface literal
{
}

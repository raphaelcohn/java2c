package com.java2c.model.attributes.packages;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
    Use this annotation to apply compilation settings, eg '-O3'. Not sure whether this will survive.
 */
@Retention(RUNTIME)
@Target(PACKAGE)
public @interface PackageGccCompilationOptions
{
	@NotNull String[] value() default "";
}

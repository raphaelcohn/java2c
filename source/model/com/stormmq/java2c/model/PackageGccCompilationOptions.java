package com.stormmq.java2c.model;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(PACKAGE)
public @interface PackageGccCompilationOptions
{
	@NotNull String[] value() default "";
}

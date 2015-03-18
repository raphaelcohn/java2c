package com.java2c.model.attributes.functions;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.java2c.model.attributes.functions.Purity.Pure;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SuppressWarnings("AnnotationNamingConvention")
@Retention(RUNTIME)
@Target(METHOD)
public @interface pure
{
	// May be defined on an interface...
	// Pure functions do not mutate state
	// Causes __attribute((pure))__ to be appended

	// Pure => __attribute((pure))__
	// Constant => __attribute((pure))__ __attribute((const))__ , ie a const function, one that does not use pointers, does not return void and does not read from or write to memory (eg simple math functions)
	@NotNull Purity value() default Pure;
}

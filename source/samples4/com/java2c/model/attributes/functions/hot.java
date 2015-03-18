package com.java2c.model.attributes.functions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SuppressWarnings("AnnotationNamingConvention")
@Retention(RUNTIME)
@Target(METHOD)
public @interface hot
{
	// May be defined on an interface, in which hot is enforced everywhere...
	// Note that we could also use this as a hint to use -O3...
	// Causes __attribute((hot))__ to be appended
}

package com.java2c.model.attributes.functions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface malloc
{
	// May be defined on an interface, in which malloc is enforced everywhere...
	// Causes __attribute((malloc))__ to be appended
}

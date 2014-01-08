package com.stormmq.java2c.model.functions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD})
public @interface flatten
{
	// May be defined on an interface, in which flatten is enforced everywhere...
	// Causes __attribute((flatten))__ to be appended
}

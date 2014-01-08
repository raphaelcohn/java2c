package com.stormmq.java2c.model.functions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD})
public @interface cold
{
	// May be defined on an interface, in which cold is enforced everywhere...
	// Causes __attribute((cold))__ to be appended
}

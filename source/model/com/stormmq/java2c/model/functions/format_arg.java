package com.stormmq.java2c.model.functions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({PARAMETER})
public @interface format_arg
{
	// May be defined on an interface, in format is enforced everywhere...
	// Causes __attribute((format, INDEX))__ to be appended where INDEX is a 1-based index
}

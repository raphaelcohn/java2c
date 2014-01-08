package com.stormmq.java2c.model.functions;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({PARAMETER})
public @interface format
{
	// May be defined on an interface, in format_arg is enforced everywhere...
	// Causes __attribute((format, FormatArgArchetype, INDEX of @format_arg, INDEX of varargs))__ to be appended where INDEX is a 1-based index
	// INDEX of varargs is 0 if we are a va_list function.

	@NotNull FormatArgArchetype	value();
}

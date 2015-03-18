package com.stormmq.java2c.model.functions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SuppressWarnings("AnnotationNamingConvention")
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface sentinel
{
	// May be defined on an interface, in which sentinel is enforced everywhere...
	// Causes __attribute((sentinel (VALUE)))__ to be appended
	// Must ONLY be used by vararg functions like execl and execlp. For execle it is set to 1
	// It is for functions where varargs are terminated by a NULL
	// The value is the offset from the end of the varargs
	int value() default 0;
}

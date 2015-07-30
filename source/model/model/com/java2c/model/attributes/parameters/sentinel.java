/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.model.attributes.parameters;

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

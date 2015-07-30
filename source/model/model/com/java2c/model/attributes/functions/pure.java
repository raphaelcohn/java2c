/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

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

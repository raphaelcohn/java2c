package com.stormmq.java2c.model.functions;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.stormmq.java2c.model.functions.Inlining.Inline;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD})
public @interface inline
{
	// May be defined on an interface, in which inlining is enforced everywhere...

	/*
		Never: __attribute(noinline)__
		CompilerChoice: do nothing
		Inline: inline
		Force: inline AND __attribute(always_inline)__
	 */

	@NotNull Inlining value() default Inline;
}

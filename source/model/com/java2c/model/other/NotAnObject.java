package com.java2c.model.other;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
    Currently being used to document that header file wrappers are exactly that
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface NotAnObject
{
}

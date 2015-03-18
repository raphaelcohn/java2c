package com.java2c.samples4;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Target({TYPE, CONSTRUCTOR, METHOD})
@Retention(SOURCE)
public @interface CIncludes
{
}

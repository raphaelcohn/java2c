package com.java2c.model.other;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
    Currently being used for a static constructor method on some classes (eg int16_t) but NOT ALL - only where there is a #define constructor we can wrap

    Also being used in the broken va_list logic


    Documents that a value MUST be a literal

 */
@SuppressWarnings("AnnotationNamingConvention")
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface literal
{
}

package com.java2c.transpiler.elementConverters;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;

public interface ElementConverter<E extends Element>
{
	void convert(@NonNls @NotNull final Messager messager, @NotNull final E element) throws ConversionException;
}

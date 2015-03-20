package com.java2c.model.types.scalars;

import org.jetbrains.annotations.NotNull;

public interface SignedMathematics<T> extends Mathematics<T>
{
	@NotNull
	T negate();
}

package com.java2c.model.scalars;

import org.jetbrains.annotations.NotNull;

public interface BooleanAlgebra<T>
{
	// TODO: CCodeTemplate
	T and(@NotNull T right);

	// TODO: CCodeTemplate
	T or(@NotNull T right);
}

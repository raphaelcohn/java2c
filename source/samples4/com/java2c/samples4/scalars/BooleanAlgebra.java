package com.java2c.samples4.scalars;

import com.java2c.samples4.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

public interface BooleanAlgebra<T>
{
	// TODO: CCodeTemplate
	T and(@NotNull T right);

	// TODO: CCodeTemplate
	T or(@NotNull T right);

	@CCodeTemplate("(!@this@)")
	@NotNull
	T not();
}

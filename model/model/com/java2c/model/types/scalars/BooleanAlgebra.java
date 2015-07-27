package com.java2c.model.types.scalars;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.CType;
import com.java2c.model.types.Uncastable;
import org.jetbrains.annotations.NotNull;

@Uncastable
public interface BooleanAlgebra<T> extends CType
{
	@CCodeTemplate("(@this@ && @right@)")
	T and(@NotNull final T right);

	@CCodeTemplate("(@this@ || @right@)")
	T or(@NotNull final T right);
}

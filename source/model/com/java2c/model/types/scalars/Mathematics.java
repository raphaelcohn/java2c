package com.java2c.model.types.scalars;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.CType;
import com.java2c.model.types.Uncastable;
import org.jetbrains.annotations.NotNull;

@Uncastable
public interface Mathematics<T> extends CType
{
	@CCodeTemplate("(@this@ + @right@)")
	@NotNull
	T add(@NotNull final T right);

	@CCodeTemplate("(@this@ - @right@)")
	@NotNull
	T subtract(@NotNull final T right);

	@CCodeTemplate("(@this@ * @right@)")
	@NotNull
	T multiply(@NotNull final T right);

	@SuppressWarnings("HardcodedFileSeparator")
	@CCodeTemplate("(@this@ / @right@)")
	@NotNull
	T divide(@NotNull final T right);

	@CCodeTemplate("(@this@ % @right@)")
	@NotNull
	T modulus(@NotNull final T right);
}

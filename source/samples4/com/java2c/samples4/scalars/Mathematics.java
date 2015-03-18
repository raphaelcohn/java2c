package com.java2c.samples4.scalars;

import com.java2c.samples4.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

public interface Mathematics<T>
{
	@CCodeTemplate("(@this@ + @right@)")
	@NotNull
	T add(@NotNull T right);

	@CCodeTemplate("(@this@ - @right@)")
	@NotNull
	T subtract(@NotNull T right);

	@CCodeTemplate("(@this@ * @right@)")
	@NotNull
	T multiply(@NotNull T right);

	@SuppressWarnings("HardcodedFileSeparator")
	@CCodeTemplate("(@this@ / @right@)")
	@NotNull
	T divide(@NotNull T right);

	@CCodeTemplate("(@this@ % @right@)")
	@NotNull
	T modulus(@NotNull T right);
}

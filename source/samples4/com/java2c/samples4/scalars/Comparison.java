package com.java2c.samples4.scalars;

import com.java2c.samples4.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

public interface Comparison<T>
{
	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	@CCodeTemplate("(@this@ > @right@)")
	boolean greaterThan(@NotNull T right);

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	@CCodeTemplate("(@this@ < @right@)")
	boolean lessThan(@NotNull T right);

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	@CCodeTemplate("(@this@ >= @right@)")
	boolean greaterThanOrEqualTo(@NotNull T right);

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	@CCodeTemplate("(@this@ <= @right@)")
	boolean lessThanOrEqualTo(@NotNull T right);
}

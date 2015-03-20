package com.java2c.model.types.scalars;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.CType;
import com.java2c.model.types.Uncastable;
import org.jetbrains.annotations.NotNull;

@Uncastable
public interface Comparison<T> extends CType
{
	@CCodeTemplate("(@this@ > @right@)")
	boolean isGreaterThan(@NotNull final T right);

	@CCodeTemplate("(@this@ < @right@)")
	boolean isLessThan(@NotNull final T right);

	@CCodeTemplate("(@this@ >= @right@)")
	boolean isGreaterThanOrEqualTo(@NotNull final T right);

	@CCodeTemplate("(@this@ <= @right@)")
	boolean isLessThanOrEqualTo(@NotNull final T right);
}

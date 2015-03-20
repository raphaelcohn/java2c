package com.java2c.model.types.scalars;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.CType;
import com.java2c.model.types.Uncastable;
import org.jetbrains.annotations.NotNull;

@Uncastable
public interface Equality<T> extends CType
{
	@CCodeTemplate("(@this@ == @right@)")
	boolean isEqual(@NotNull final T right);

	@CCodeTemplate("(@this@ != @right@)")
	boolean isNotEqual(@NotNull final T right);
}

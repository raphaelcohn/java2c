package com.java2c.samples4.scalars;

import com.java2c.samples4.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

public interface Equality<T>
{
	@SuppressWarnings("MethodOverloadsMethodOfSuperclass")
	@CCodeTemplate("(@this@ == @right@)")
	boolean equals(@NotNull T right);

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	@CCodeTemplate("(@this@ != @right@)")
	boolean notEquals(@NotNull T right);
}

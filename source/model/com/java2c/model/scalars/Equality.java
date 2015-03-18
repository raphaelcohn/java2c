package com.java2c.model.scalars;

import com.java2c.model.other.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

public interface Equality<T>
{
	@SuppressWarnings({"MethodOverloadsMethodOfSuperclass", "BooleanMethodNameMustStartWithQuestion", "MisspelledEquals"})
	@CCodeTemplate("(@this@ == @right@)")
	boolean equal(@NotNull T right);

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	@CCodeTemplate("(@this@ != @right@)")
	boolean notEqual(@NotNull T right);
}

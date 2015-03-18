package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.CCode;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "double_t", systemHeader = "float.h")
public class double_t
{
	protected final double value;

	public double_t(final double value)
	{
		this.value = value;
	}

	@CCode("(double) this")
	@NotNull
	public final double_ castDouble()
	{
		return new double_(value);
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters", "StaticMethodNamingConvention"})
	@NotNull
	public static double_t double_t(final double value)
	{
		return new double_t(value);
	}
}

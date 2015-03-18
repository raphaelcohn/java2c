package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion("double")
public class double_ extends Primitive
{
	protected final double value;

	public double_(final double value)
	{
		this.value = value;
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters", "StaticMethodNamingConvention"})
	@NotNull
	public static double_ double_(final double value)
	{
		return new double_(value);
	}
}

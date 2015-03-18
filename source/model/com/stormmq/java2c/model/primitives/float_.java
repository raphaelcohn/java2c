package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion("float")
public class float_ extends Primitive
{
	protected final float value;

	public float_(final float value)
	{
		this.value = value;
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters", "StaticMethodNamingConvention"})
	@NotNull
	public static float_ float_(final float value)
	{
		return new float_(value);
	}
}

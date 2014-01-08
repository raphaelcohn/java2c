package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@PrimitiveConversion("double")
public class double_ extends Primitive
{
	private final double value;

	public double_(final double value)
	{
		this.value = value;
	}
}

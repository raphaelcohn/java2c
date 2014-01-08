package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@PrimitiveConversion("float")
public class float_ extends Primitive
{
	private final float value;

	public float_(final float value)
	{
		this.value = value;
	}
}

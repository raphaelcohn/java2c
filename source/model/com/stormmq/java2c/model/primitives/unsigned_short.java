package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@PrimitiveConversion("unsigned short")
public class unsigned_short extends Primitive
{
	private final char value;

	public unsigned_short(final char value)
	{
		this.value = value;
	}
}

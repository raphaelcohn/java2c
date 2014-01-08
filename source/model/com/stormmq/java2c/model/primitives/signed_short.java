package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@PrimitiveConversion("signed short")
public class signed_short extends Primitive
{
	private final short value;

	public signed_short(final short value)
	{
		this.value = value;
	}
}

package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@PrimitiveConversion("signed long long")
public class signed_long_long extends Primitive
{
	private final long value;

	public signed_long_long(final long value)
	{
		this.value = value;
	}
}

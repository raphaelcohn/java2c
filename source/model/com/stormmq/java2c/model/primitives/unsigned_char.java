package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "unsigned char", isUnsigned = true)
public class unsigned_char extends Primitive
{
	protected final byte value;

	public unsigned_char(final byte value)
	{
		this.value = value;
	}
}

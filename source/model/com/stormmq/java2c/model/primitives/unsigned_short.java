package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "unsigned short", isUnsigned = true)
public class unsigned_short extends Primitive
{
	protected final char value;

	public unsigned_short(final char value)
	{
		this.value = value;
	}
}

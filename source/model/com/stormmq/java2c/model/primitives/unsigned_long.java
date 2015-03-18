package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "unsigned long", isUnsigned = true)
public class unsigned_long extends Primitive
{
	protected final int value;

	// In practice, this is a 64-bit quantity on 64-bit Linux
	public unsigned_long(final int value)
	{
		this.value = value;
	}
}

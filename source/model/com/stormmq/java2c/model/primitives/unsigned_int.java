package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "unsigned int", isUnsigned = true)
public class unsigned_int extends Primitive
{
	protected final char value;

	// Technically, can be 16-bit, but in practice this is no longer true; it's 32-bit on 32-bit and 64-bit Linux
	public unsigned_int(final char value)
	{
		this.value = value;
	}
}

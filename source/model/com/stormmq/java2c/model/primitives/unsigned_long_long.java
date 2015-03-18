package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "unsigned long long", isUnsigned = true)
public class unsigned_long_long extends Primitive
{
	protected final long value;

	public unsigned_long_long(final long value)
	{
		this.value = value;
	}
}

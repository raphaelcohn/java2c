package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion("signed long long")
public class signed_long_long extends Primitive
{
	protected final long value;

	public signed_long_long(final long value)
	{
		this.value = value;
	}
}

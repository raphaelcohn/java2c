package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion("signed char")
public class signed_char extends Primitive
{
	protected final byte value;

	public signed_char(final byte value)
	{
		this.value = value;
	}
}

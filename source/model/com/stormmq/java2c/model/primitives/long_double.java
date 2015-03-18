package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion("long double")
public class long_double extends Primitive
{
	protected final double value;

	public long_double(final double value)
	{
		this.value = value;
	}
}

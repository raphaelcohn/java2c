package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

// For completeness. In practice, this type is so variable as to useless.
@PrimitiveConversion("long double")
public class long_double extends Primitive
{
	private final double value;

	public long_double(final double value)
	{
		this.value = value;
	}
}

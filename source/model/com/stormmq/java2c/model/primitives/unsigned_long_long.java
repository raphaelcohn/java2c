package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import com.stormmq.java2c.model.variables.unsigned;

@PrimitiveConversion("unsigned long long")
public class unsigned_long_long extends Primitive
{
	@unsigned private final long value;

	public unsigned_long_long(@unsigned final long value)
	{
		this.value = value;
	}
}

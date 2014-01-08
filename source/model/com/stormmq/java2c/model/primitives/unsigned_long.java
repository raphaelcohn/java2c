package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import com.stormmq.java2c.model.variables.unsigned;

@PrimitiveConversion("unsigned long")
public class unsigned_long extends Primitive
{
	@unsigned private final int value;

	// In practice, this is a 64-bit quantity on 64-bit Linux
	public unsigned_long(@unsigned final int value)
	{
		this.value = value;
	}
}

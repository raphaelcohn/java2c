package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import com.stormmq.java2c.model.variables.unsigned;

@PrimitiveConversion("unsigned char")
public class unsigned_char extends Primitive
{
	@unsigned private final byte value;

	public unsigned_char(@unsigned final byte value)
	{
		this.value = value;
	}
}

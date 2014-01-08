package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.variables.unsigned;

// usually is  typedef unsigned long size_t  but there is no g'tee
@PrimitiveConversion(value = "size_t", systemHeader = "stddef.h")
public class size_t extends unsigned_long
{
	// Problematic, as varies as 32-bit or 64-bit
	// Support for 64-bitness would be preferred
	public size_t(@unsigned final int value)
	{
		super(value);
	}

}

package com.stormmq.java2c.model.primitives;

// usually is  typedef unsigned long size_t  but there is no g'tee
@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "size_t", systemHeader = "stddef.h", isUnsigned = true)
public class size_t extends unsigned_long
{
	// Problematic, as varies as 32-bit or 64-bit
	// Support for 64-bitness would be preferred
	public size_t(final int value)
	{
		super(value);
	}

}

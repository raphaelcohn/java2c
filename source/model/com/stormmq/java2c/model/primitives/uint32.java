package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "uint32_t", systemHeader = "stdint.h", isUnsigned = true)
public class uint32 extends Primitive
{
	protected final long value;

	public uint32(final int value)
	{
		this.value = 0xFFFFFFFFL & (long) value;
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters"})
	@NotNull
	public static uint32 uint32(final int value)
	{
		return new uint32(value);
	}

	// @uint32 / @unsigned
	@SuppressWarnings("NumericCastThatLosesPrecision")
	public final int toSignedIntegerRepresentation()
	{
		return (int) value;
	}
}

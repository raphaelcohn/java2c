package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "uint8_t", systemHeader = "stdint.h", isUnsigned = true)
public class uint8 extends Primitive
{
	@SuppressWarnings("ConstantNamingConvention") private static final int xFF = 0xFF;

	protected final short value;

	@SuppressWarnings("NumericCastThatLosesPrecision")
	public uint8(final byte value)
	{
		this.value = (short) (xFF & (int) value);
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters"})
	@NotNull
	public static uint8 uint8(final byte value)
	{
		return new uint8(value);
	}

	@SuppressWarnings("NumericCastThatLosesPrecision")
	public final byte toSignedIntegerRepresentation()
	{
		return (byte) (xFF & (int) value);
	}
}

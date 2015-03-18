package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion("signed int")
public class signed_int extends Primitive
{
	protected final short value;

	// Technically, can be 16-bit, but in practice this is no longer true; it's 32-bit on 32-bit and 64-bit Linux
	public signed_int(final short value)
	{
		this.value = value;
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters", "StaticMethodNamingConvention"})
	@NotNull
	public static signed_int signed_int(final short literal)
	{
		return new signed_int(literal);
	}
}

package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion("char")
public class char_ extends Primitive
{
	protected final byte value;

	// In practice, this is a a signed char on Linux for x86, and unsigned for PowerPC and ARM
	// Determined by ABI. The options -funsigned-char and -fsigned-char change the default.
	// It is probably most sensible to only use char for strings, and avoid its use everywhere else.
	// And, in using this value, to go 'the Java way' and treat it as signed (certainly, we're less likely to screw up).
	public char_(final byte value)
	{
		this.value = value;
	}
}

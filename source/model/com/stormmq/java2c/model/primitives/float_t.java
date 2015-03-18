package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.CCode;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "float_t", systemHeader = "float.h")
public class float_t
{
	protected final float value;

	// in practice, this is a long double on Linux and a float on Mac OS X...
	public float_t(final float value)
	{
		this.value = value;
	}

	@CCode("(float) this")
	@NotNull
	public final float_ castFloat()
	{
		return new float_(value);
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters", "StaticMethodNamingConvention"})
	@NotNull
	public static float_t float_t(final float value)
	{
		return new float_t(value);
	}
}

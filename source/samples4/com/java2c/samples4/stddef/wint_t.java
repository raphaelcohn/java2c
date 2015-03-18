package com.java2c.samples4.stddef;

import com.java2c.samples4.AbstractScalar;
import com.java2c.samples4.CCodeTemplate;
import com.java2c.samples4.Scalar;
import com.java2c.samples4.scalars.BooleanAlgebra;
import com.java2c.samples4.scalars.Comparison;
import com.java2c.samples4.scalars.Equality;
import com.java2c.samples4.scalars.Mathematics;
import com.java2c.samples4.stdint.int32_t;
import org.jetbrains.annotations.NotNull;

import static com.java2c.samples4.CCodeTemplate.Scalar;
import static com.java2c.samples4.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "wint_t", includes = stddef.stddef)
public final class wint_t extends AbstractScalar<wint_t> implements Equality<wint_t>, Comparison<wint_t>, Mathematics<wint_t>, BooleanAlgebra<wint_t>
{
	// Yes, the preprocessor constant is in a different header!
	@CCodeTemplate(value = "WINT_MIN", includes = stdint)
	public static final wint_t WINT_MIN = int32_t.INT32_MIN.cast(wint_t.class);

	// Yes, the preprocessor constant is in a different header!
	@CCodeTemplate(value = "WINT_MAX", includes = stdint)
	public static final wint_t WINT_MAX = int32_t.INT32_MAX.cast(wint_t.class);

	@CCodeTemplate(Scalar)
	public wint_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected wint_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new wint_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final wint_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final wint_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final wint_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final wint_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final wint_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final wint_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public wint_t add(@NotNull final wint_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public wint_t subtract(@NotNull final wint_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public wint_t multiply(@NotNull final wint_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public wint_t divide(@NotNull final wint_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public wint_t modulus(@NotNull final wint_t right)
	{
		return super.modulus(right);
	}

	@Override
	public wint_t and(@NotNull final wint_t right)
	{
		return super.and(right);
	}

	@Override
	public wint_t or(@NotNull final wint_t right)
	{
		return super.or(right);
	}
}

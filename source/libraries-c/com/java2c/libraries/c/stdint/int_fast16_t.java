package com.java2c.libraries.c.stdint;

import com.java2c.model.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.scalars.Scalar;
import com.java2c.model.scalars.BooleanAlgebra;
import com.java2c.model.scalars.Comparison;
import com.java2c.model.scalars.Equality;
import com.java2c.model.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int_fast16_t", includes = stdint)
public final class int_fast16_t extends AbstractScalar<int_fast16_t> implements Equality<int_fast16_t>, Comparison<int_fast16_t>, Mathematics<int_fast16_t>, BooleanAlgebra<int_fast16_t>
{
	@CCodeTemplate(value = "INT16_FAST_MAX", includes = stdint)
	public static final int_fast16_t INT16_FAST_MAX = int16_t.INT16_MAX.cast(int_fast16_t.class);

	@CCodeTemplate(value = "INT16_FAST_MIN", includes = stdint)
	public static final int_fast16_t INT16_FAST_MIN = int16_t.INT16_MIN.cast(int_fast16_t.class);

	@CCodeTemplate(Scalar)
	public int_fast16_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int_fast16_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int_fast16_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final int_fast16_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final int_fast16_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final int_fast16_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final int_fast16_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final int_fast16_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final int_fast16_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int_fast16_t add(@NotNull final int_fast16_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int_fast16_t subtract(@NotNull final int_fast16_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int_fast16_t multiply(@NotNull final int_fast16_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int_fast16_t divide(@NotNull final int_fast16_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int_fast16_t modulus(@NotNull final int_fast16_t right)
	{
		return super.modulus(right);
	}

	@Override
	public int_fast16_t and(@NotNull final int_fast16_t right)
	{
		return super.and(right);
	}

	@Override
	public int_fast16_t or(@NotNull final int_fast16_t right)
	{
		return super.or(right);
	}
}

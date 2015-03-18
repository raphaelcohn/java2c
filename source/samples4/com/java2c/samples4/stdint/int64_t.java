package com.java2c.samples4.stdint;

import com.java2c.samples4.AbstractScalar;
import com.java2c.samples4.CCodeTemplate;
import com.java2c.samples4.Scalar;
import com.java2c.samples4.literal;
import com.java2c.samples4.scalars.BooleanAlgebra;
import com.java2c.samples4.scalars.Comparison;
import com.java2c.samples4.scalars.Equality;
import com.java2c.samples4.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.samples4.CCodeTemplate.Scalar;
import static com.java2c.samples4.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int64_t", includes = stdint)
public final class int64_t extends AbstractScalar<int64_t> implements Equality<int64_t>, Comparison<int64_t>, Mathematics<int64_t>, BooleanAlgebra<int64_t>
{
	@CCodeTemplate(value = "INT64_MAX", includes = stdint)
	public static final int64_t INT64_MAX = new int64_t(9223372036854775807L);

	@CCodeTemplate(value = "INT64_MIN", includes = stdint)
	public static final int64_t INT64_MIN = new int64_t(-INT64_MAX.value - 1L);

	@CCodeTemplate(value = "INT64_C(value)", includes = stdint)
	public static int64_t literal(@literal final long value)
	{
		return new int64_t(value);
	}

	@CCodeTemplate(Scalar)
	public int64_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int64_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int64_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final int64_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final int64_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final int64_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final int64_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final int64_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final int64_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int64_t add(@NotNull final int64_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int64_t subtract(@NotNull final int64_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int64_t multiply(@NotNull final int64_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int64_t divide(@NotNull final int64_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int64_t modulus(@NotNull final int64_t right)
	{
		return super.modulus(right);
	}

	@Override
	public int64_t and(@NotNull final int64_t right)
	{
		return super.and(right);
	}

	@Override
	public int64_t or(@NotNull final int64_t right)
	{
		return super.or(right);
	}
}

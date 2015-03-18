package com.java2c.samples4.stdint;

import com.java2c.samples4.AbstractScalar;
import com.java2c.samples4.CCodeTemplate;
import com.java2c.samples4.Scalar;
import com.java2c.samples4.scalars.BooleanAlgebra;
import com.java2c.samples4.scalars.Comparison;
import com.java2c.samples4.scalars.Equality;
import com.java2c.samples4.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.samples4.CCodeTemplate.Scalar;
import static com.java2c.samples4.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int_least32_t", includes = stdint)
public final class int_least32_t extends AbstractScalar<int_least32_t> implements Equality<int_least32_t>, Comparison<int_least32_t>, Mathematics<int_least32_t>, BooleanAlgebra<int_least32_t>
{
	@CCodeTemplate(value = "INT32_LEAST_MAX", includes = stdint)
	public static final int_least32_t INT32_LEAST_MAX = int32_t.INT32_MAX.cast(int_least32_t.class);

	@CCodeTemplate(value = "INT32_LEAST_MIN", includes = stdint)
	public static final int_least32_t INT32_LEAST_MIN = int32_t.INT32_MIN.cast(int_least32_t.class);

	@CCodeTemplate(Scalar)
	public int_least32_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int_least32_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int_least32_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final int_least32_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final int_least32_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final int_least32_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final int_least32_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final int_least32_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final int_least32_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int_least32_t add(@NotNull final int_least32_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int_least32_t subtract(@NotNull final int_least32_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int_least32_t multiply(@NotNull final int_least32_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int_least32_t divide(@NotNull final int_least32_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int_least32_t modulus(@NotNull final int_least32_t right)
	{
		return super.modulus(right);
	}

	@Override
	public int_least32_t and(@NotNull final int_least32_t right)
	{
		return super.and(right);
	}

	@Override
	public int_least32_t or(@NotNull final int_least32_t right)
	{
		return super.or(right);
	}
}
package com.java2c.libraries.c.stdint;

import com.java2c.model.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.scalars.Scalar;
import com.java2c.model.other.literal;
import com.java2c.model.scalars.BooleanAlgebra;
import com.java2c.model.scalars.Comparison;
import com.java2c.model.scalars.Equality;
import com.java2c.model.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int32_t", includes = stdint)
public final class int32_t extends AbstractScalar<int32_t> implements Equality<int32_t>, Comparison<int32_t>, Mathematics<int32_t>, BooleanAlgebra<int32_t>
{
	@CCodeTemplate(value = "INT32_MAX", includes = stdint)
	public static final int32_t INT32_MAX = new int32_t(2147483647L);

	@CCodeTemplate(value = "INT32_MIN", includes = stdint)
	public static final int32_t INT32_MIN = new int32_t(-INT32_MAX.value - 1L);

	@CCodeTemplate(value = "INT32_C(value)", includes = stdint)
	public static int32_t literal(@literal final long value)
	{
		return new int32_t(value);
	}

	@CCodeTemplate(Scalar)
	public int32_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int32_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int32_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final int32_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final int32_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final int32_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final int32_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final int32_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final int32_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int32_t add(@NotNull final int32_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int32_t subtract(@NotNull final int32_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int32_t multiply(@NotNull final int32_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int32_t divide(@NotNull final int32_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int32_t modulus(@NotNull final int32_t right)
	{
		return super.modulus(right);
	}

	@Override
	public int32_t and(@NotNull final int32_t right)
	{
		return super.and(right);
	}

	@Override
	public int32_t or(@NotNull final int32_t right)
	{
		return super.or(right);
	}
}

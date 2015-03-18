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
@Scalar(value = "uint_least8_t", signed = false, includes = stdint)
public final class uint_least8_t extends AbstractScalar<uint_least8_t> implements Equality<uint_least8_t>, Comparison<uint_least8_t>, Mathematics<uint_least8_t>, BooleanAlgebra<uint_least8_t>
{
	@CCodeTemplate(value = "UINT8_LEAST_MAX", includes = stdint)
	public static final uint_least8_t UINT8_LEAST_MAX = uint8_t.UINT8_MAX.cast(uint_least8_t.class);

	@CCodeTemplate(Scalar)
	public uint_least8_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint_least8_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint_least8_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final uint_least8_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final uint_least8_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final uint_least8_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final uint_least8_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final uint_least8_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final uint_least8_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint_least8_t add(@NotNull final uint_least8_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint_least8_t subtract(@NotNull final uint_least8_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint_least8_t multiply(@NotNull final uint_least8_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint_least8_t divide(@NotNull final uint_least8_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint_least8_t modulus(@NotNull final uint_least8_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint_least8_t and(@NotNull final uint_least8_t right)
	{
		return super.and(right);
	}

	@Override
	public uint_least8_t or(@NotNull final uint_least8_t right)
	{
		return super.or(right);
	}
}
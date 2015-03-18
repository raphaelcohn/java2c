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
@Scalar(value = "uint64_t", signed = false, includes = stdint)
public final class uint64_t extends AbstractScalar<uint64_t> implements Equality<uint64_t>, Comparison<uint64_t>, Mathematics<uint64_t>, BooleanAlgebra<uint64_t>
{
	@CCodeTemplate(value = "UINT64_MAX", includes = stdint)
	public static final uint64_t UINT64_MAX = new uint64_t(0xFFFFFFFFFFFFFFFFL);

	@CCodeTemplate(value = "UINT64_C(value)", includes = stdint)
	public static uint64_t literal(@literal final long value)
	{
		return new uint64_t(value);
	}

	@CCodeTemplate(Scalar)
	public uint64_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint64_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint64_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final uint64_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final uint64_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final uint64_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final uint64_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final uint64_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final uint64_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint64_t add(@NotNull final uint64_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint64_t subtract(@NotNull final uint64_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint64_t multiply(@NotNull final uint64_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint64_t divide(@NotNull final uint64_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint64_t modulus(@NotNull final uint64_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint64_t and(@NotNull final uint64_t right)
	{
		return super.and(right);
	}

	@Override
	public uint64_t or(@NotNull final uint64_t right)
	{
		return super.or(right);
	}
}

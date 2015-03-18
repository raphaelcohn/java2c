package com.java2c.samples4.builtin;

import com.java2c.samples4.AbstractScalar;
import com.java2c.samples4.CCodeTemplate;
import com.java2c.samples4.Scalar;
import com.java2c.samples4.scalars.BooleanAlgebra;
import com.java2c.samples4.scalars.Comparison;
import com.java2c.samples4.scalars.Equality;
import com.java2c.samples4.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

// uint64_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned long long", signed = false)
public final class c_unsigned_long_long extends AbstractScalar<c_unsigned_long_long> implements Equality<c_unsigned_long_long>, Comparison<c_unsigned_long_long>, Mathematics<c_unsigned_long_long>, BooleanAlgebra<c_unsigned_long_long>
{
	@CCodeTemplate(CCodeTemplate.Scalar)
	public c_unsigned_long_long(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected c_unsigned_long_long constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new c_unsigned_long_long(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final c_unsigned_long_long right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final c_unsigned_long_long right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final c_unsigned_long_long right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final c_unsigned_long_long right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final c_unsigned_long_long right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final c_unsigned_long_long right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public c_unsigned_long_long add(@NotNull final c_unsigned_long_long right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public c_unsigned_long_long subtract(@NotNull final c_unsigned_long_long right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public c_unsigned_long_long multiply(@NotNull final c_unsigned_long_long right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public c_unsigned_long_long divide(@NotNull final c_unsigned_long_long right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public c_unsigned_long_long modulus(@NotNull final c_unsigned_long_long right)
	{
		return super.modulus(right);
	}

	@Override
	public c_unsigned_long_long and(@NotNull final c_unsigned_long_long right)
	{
		return super.and(right);
	}

	@Override
	public c_unsigned_long_long or(@NotNull final c_unsigned_long_long right)
	{
		return super.or(right);
	}
}

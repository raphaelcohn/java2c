package com.java2c.samples4.builtin;

import com.java2c.samples4.AbstractScalar;
import com.java2c.samples4.CCodeTemplate;
import com.java2c.samples4.Scalar;
import com.java2c.samples4.scalars.BooleanAlgebra;
import com.java2c.samples4.scalars.Comparison;
import com.java2c.samples4.scalars.Equality;
import com.java2c.samples4.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

// uint16_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned short", signed = false)
public final class c_unsigned_short extends AbstractScalar<c_unsigned_short> implements Equality<c_unsigned_short>, Comparison<c_unsigned_short>, Mathematics<c_unsigned_short>, BooleanAlgebra<c_unsigned_short>
{
	@CCodeTemplate(CCodeTemplate.Scalar)
	public c_unsigned_short(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected c_unsigned_short constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new c_unsigned_short(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final c_unsigned_short right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final c_unsigned_short right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final c_unsigned_short right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final c_unsigned_short right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final c_unsigned_short right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final c_unsigned_short right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public c_unsigned_short add(@NotNull final c_unsigned_short right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public c_unsigned_short subtract(@NotNull final c_unsigned_short right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public c_unsigned_short multiply(@NotNull final c_unsigned_short right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public c_unsigned_short divide(@NotNull final c_unsigned_short right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public c_unsigned_short modulus(@NotNull final c_unsigned_short right)
	{
		return super.modulus(right);
	}

	@Override
	public c_unsigned_short and(@NotNull final c_unsigned_short right)
	{
		return super.and(right);
	}

	@Override
	public c_unsigned_short or(@NotNull final c_unsigned_short right)
	{
		return super.or(right);
	}
}

package com.java2c.libraries.builtin;

import com.java2c.model.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.scalars.Scalar;
import com.java2c.model.scalars.BooleanAlgebra;
import com.java2c.model.scalars.Comparison;
import com.java2c.model.scalars.Equality;
import com.java2c.model.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;

// uint8_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar("signed char")
public final class c_signed_char extends AbstractScalar<c_signed_char> implements Equality<c_signed_char>, Comparison<c_signed_char>, Mathematics<c_signed_char>, BooleanAlgebra<c_signed_char>
{
	@CCodeTemplate(Scalar)
	public c_signed_char(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected c_signed_char constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new c_signed_char(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final c_signed_char right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final c_signed_char right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final c_signed_char right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final c_signed_char right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final c_signed_char right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final c_signed_char right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public c_signed_char add(@NotNull final c_signed_char right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public c_signed_char subtract(@NotNull final c_signed_char right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public c_signed_char multiply(@NotNull final c_signed_char right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public c_signed_char divide(@NotNull final c_signed_char right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public c_signed_char modulus(@NotNull final c_signed_char right)
	{
		return super.modulus(right);
	}

	@Override
	public c_signed_char and(@NotNull final c_signed_char right)
	{
		return super.and(right);
	}

	@Override
	public c_signed_char or(@NotNull final c_signed_char right)
	{
		return super.or(right);
	}
}

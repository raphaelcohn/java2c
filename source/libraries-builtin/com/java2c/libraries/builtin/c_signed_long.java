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

@SuppressWarnings("ClassNamingConvention")
@Scalar("signed long")
public final class c_signed_long extends AbstractScalar<c_signed_long> implements Equality<c_signed_long>, Comparison<c_signed_long>, Mathematics<c_signed_long>, BooleanAlgebra<c_signed_long>
{
	@CCodeTemplate(Scalar)
	public c_signed_long(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected c_signed_long constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new c_signed_long(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equal(@NotNull final c_signed_long right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEqual(@NotNull final c_signed_long right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final c_signed_long right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final c_signed_long right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final c_signed_long right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final c_signed_long right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public c_signed_long add(@NotNull final c_signed_long right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public c_signed_long subtract(@NotNull final c_signed_long right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public c_signed_long multiply(@NotNull final c_signed_long right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public c_signed_long divide(@NotNull final c_signed_long right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public c_signed_long modulus(@NotNull final c_signed_long right)
	{
		return super.modulus(right);
	}

	@Override
	public c_signed_long and(@NotNull final c_signed_long right)
	{
		return super.and(right);
	}

	@Override
	public c_signed_long or(@NotNull final c_signed_long right)
	{
		return super.or(right);
	}
}

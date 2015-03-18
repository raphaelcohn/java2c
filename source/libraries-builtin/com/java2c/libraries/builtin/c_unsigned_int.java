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

// uint32_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned int", signed = false)
public final class c_unsigned_int extends AbstractScalar<c_unsigned_int> implements Equality<c_unsigned_int>, Comparison<c_unsigned_int>, Mathematics<c_unsigned_int>, BooleanAlgebra<c_unsigned_int>
{
	@CCodeTemplate(Scalar)
	public c_unsigned_int(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected c_unsigned_int constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new c_unsigned_int(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equal(@NotNull final c_unsigned_int right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEqual(@NotNull final c_unsigned_int right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final c_unsigned_int right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final c_unsigned_int right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final c_unsigned_int right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final c_unsigned_int right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public c_unsigned_int add(@NotNull final c_unsigned_int right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public c_unsigned_int subtract(@NotNull final c_unsigned_int right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public c_unsigned_int multiply(@NotNull final c_unsigned_int right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public c_unsigned_int divide(@NotNull final c_unsigned_int right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public c_unsigned_int modulus(@NotNull final c_unsigned_int right)
	{
		return super.modulus(right);
	}

	@Override
	public c_unsigned_int and(@NotNull final c_unsigned_int right)
	{
		return super.and(right);
	}

	@Override
	public c_unsigned_int or(@NotNull final c_unsigned_int right)
	{
		return super.or(right);
	}
}

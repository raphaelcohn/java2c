package com.java2c.libraries.builtin;

import com.java2c.model.other.literal;
import com.java2c.model.types.Banished;
import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.model.types.scalars.BooleanAlgebra;
import com.java2c.model.types.scalars.Comparison;
import com.java2c.model.types.scalars.Equality;
import com.java2c.model.types.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;

// uint32_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned int", signed = false)
public final class unsigned_int extends AbstractScalar<unsigned_int> implements Equality<unsigned_int>, Comparison<unsigned_int>, Mathematics<unsigned_int>, BooleanAlgebra<unsigned_int>
{
	@CCodeTemplate(Scalar)
	public unsigned_int(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected unsigned_int constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new unsigned_int(value);
	}

	@Override
	public boolean isEqual(@NotNull final unsigned_int right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final unsigned_int right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final unsigned_int right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final unsigned_int right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final unsigned_int right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final unsigned_int right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public unsigned_int add(@NotNull final unsigned_int right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public unsigned_int subtract(@NotNull final unsigned_int right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public unsigned_int multiply(@NotNull final unsigned_int right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public unsigned_int divide(@NotNull final unsigned_int right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public unsigned_int modulus(@NotNull final unsigned_int right)
	{
		return super.modulus(right);
	}

	@Override
	public unsigned_int and(@NotNull final unsigned_int right)
	{
		return super.and(right);
	}

	@Override
	public unsigned_int or(@NotNull final unsigned_int right)
	{
		return super.or(right);
	}
}

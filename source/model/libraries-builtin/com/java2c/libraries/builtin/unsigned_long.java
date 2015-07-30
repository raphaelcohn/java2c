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

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned long", signed = false)
public final class unsigned_long extends AbstractScalar<unsigned_long> implements Equality<unsigned_long>, Comparison<unsigned_long>, Mathematics<unsigned_long>, BooleanAlgebra<unsigned_long>
{
	@CCodeTemplate(Scalar)
	public unsigned_long(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected unsigned_long constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new unsigned_long(value);
	}

	@Override
	public boolean isEqual(@NotNull final unsigned_long right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final unsigned_long right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final unsigned_long right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final unsigned_long right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final unsigned_long right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final unsigned_long right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public unsigned_long add(@NotNull final unsigned_long right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public unsigned_long subtract(@NotNull final unsigned_long right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public unsigned_long multiply(@NotNull final unsigned_long right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public unsigned_long divide(@NotNull final unsigned_long right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public unsigned_long modulus(@NotNull final unsigned_long right)
	{
		return super.modulus(right);
	}

	@Override
	public unsigned_long and(@NotNull final unsigned_long right)
	{
		return super.and(right);
	}

	@Override
	public unsigned_long or(@NotNull final unsigned_long right)
	{
		return super.or(right);
	}
}

package com.java2c.libraries.c.stdint;

import com.java2c.model.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.scalars.Scalar;
import com.java2c.model.scalars.BooleanAlgebra;
import com.java2c.model.scalars.Comparison;
import com.java2c.model.scalars.Equality;
import com.java2c.model.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "uint_fast16_t", signed = false, includes = stdint)
public final class uint_fast16_t extends AbstractScalar<uint_fast16_t> implements Equality<uint_fast16_t>, Comparison<uint_fast16_t>, Mathematics<uint_fast16_t>, BooleanAlgebra<uint_fast16_t>
{
	@CCodeTemplate(value = "UINT16_FAST_MAX", includes = stdint)
	public static final uint_fast16_t UINT16_FAST_MAX = uint16_t.UINT16_MAX.cast(uint_fast16_t.class);

	@CCodeTemplate(Scalar)
	public uint_fast16_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint_fast16_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint_fast16_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equal(@NotNull final uint_fast16_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEqual(@NotNull final uint_fast16_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final uint_fast16_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final uint_fast16_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final uint_fast16_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final uint_fast16_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint_fast16_t add(@NotNull final uint_fast16_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint_fast16_t subtract(@NotNull final uint_fast16_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint_fast16_t multiply(@NotNull final uint_fast16_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint_fast16_t divide(@NotNull final uint_fast16_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint_fast16_t modulus(@NotNull final uint_fast16_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint_fast16_t and(@NotNull final uint_fast16_t right)
	{
		return super.and(right);
	}

	@Override
	public uint_fast16_t or(@NotNull final uint_fast16_t right)
	{
		return super.or(right);
	}
}

package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.model.other.literal;
import com.java2c.model.types.scalars.BooleanAlgebra;
import com.java2c.model.types.scalars.Comparison;
import com.java2c.model.types.scalars.Equality;
import com.java2c.model.types.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "uint8_t", signed = false, includes = stdint)
public final class uint8_t extends AbstractScalar<uint8_t> implements Equality<uint8_t>, Comparison<uint8_t>, Mathematics<uint8_t>, BooleanAlgebra<uint8_t>
{
	@CCodeTemplate(value = "UINT8_MAX", includes = stdint)
	public static final uint8_t UINT8_MAX = new uint8_t(255L);

	@CCodeTemplate(value = "UINT8_C(value)", includes = stdint)
	public static uint8_t literal(@literal final long value)
	{
		return new uint8_t(value);
	}

	@CCodeTemplate(Scalar)
	public uint8_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint8_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint8_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final uint8_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final uint8_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final uint8_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final uint8_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final uint8_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final uint8_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint8_t add(@NotNull final uint8_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint8_t subtract(@NotNull final uint8_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint8_t multiply(@NotNull final uint8_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint8_t divide(@NotNull final uint8_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint8_t modulus(@NotNull final uint8_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint8_t and(@NotNull final uint8_t right)
	{
		return super.and(right);
	}

	@Override
	public uint8_t or(@NotNull final uint8_t right)
	{
		return super.or(right);
	}
}

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
@Scalar(value = "uint32_t", signed = false, includes = stdint)
public final class uint32_t extends AbstractScalar<uint32_t> implements Equality<uint32_t>, Comparison<uint32_t>, Mathematics<uint32_t>, BooleanAlgebra<uint32_t>
{
	@CCodeTemplate(value = "UINT32_MAX", includes = stdint)
	public static final uint32_t UINT32_MAX = new uint32_t(4294967295L);

	@CCodeTemplate(value = "UINT32_C(value)", includes = stdint)
	public static uint32_t literal(@literal final long value)
	{
		return new uint32_t(value);
	}

	@CCodeTemplate(Scalar)
	public uint32_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint32_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint32_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final uint32_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final uint32_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final uint32_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final uint32_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final uint32_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final uint32_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint32_t add(@NotNull final uint32_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint32_t subtract(@NotNull final uint32_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint32_t multiply(@NotNull final uint32_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint32_t divide(@NotNull final uint32_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint32_t modulus(@NotNull final uint32_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint32_t and(@NotNull final uint32_t right)
	{
		return super.and(right);
	}

	@Override
	public uint32_t or(@NotNull final uint32_t right)
	{
		return super.or(right);
	}
}

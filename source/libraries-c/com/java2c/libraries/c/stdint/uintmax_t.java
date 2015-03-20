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
import static com.java2c.libraries.c.stdint.uint64_t.UINT64_MAX;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "uintmax_t", signed = false, includes = stdint)
public final class uintmax_t extends AbstractScalar<uintmax_t> implements Equality<uintmax_t>, Comparison<uintmax_t>, Mathematics<uintmax_t>, BooleanAlgebra<uintmax_t>
{
	@CCodeTemplate(value = "UINTMAX_MAX", includes = stdint)
	@NotNull
	public static final uintmax_t UINTMAX_MAX = UINT64_MAX.cast(uintmax_t.class);

	@CCodeTemplate(value = "UINTMAX_C(value)", includes = stdint)
	@NotNull
	public static uintmax_t literal(@literal final long value)
	{
		return new uintmax_t(value);
	}

	@CCodeTemplate(Scalar)
	public uintmax_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uintmax_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uintmax_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final uintmax_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final uintmax_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final uintmax_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final uintmax_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final uintmax_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final uintmax_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uintmax_t add(@NotNull final uintmax_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uintmax_t subtract(@NotNull final uintmax_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uintmax_t multiply(@NotNull final uintmax_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uintmax_t divide(@NotNull final uintmax_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uintmax_t modulus(@NotNull final uintmax_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uintmax_t and(@NotNull final uintmax_t right)
	{
		return super.and(right);
	}

	@Override
	public uintmax_t or(@NotNull final uintmax_t right)
	{
		return super.or(right);
	}
}

package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int_least64_t", includes = stdint)
public final class int_least64_t extends AbstractScalar<int_least64_t> implements Equality<int_least64_t>, Comparison<int_least64_t>, SignedMathematics<int_least64_t>, BooleanAlgebra<int_least64_t>
{
	@CCodeTemplate(value = "INT64_LEAST_MAX", includes = stdint)
	public static final int_least64_t INT64_LEAST_MAX = int64_t.INT64_MAX.cast(int_least64_t.class);

	@CCodeTemplate(value = "INT64_LEAST_MIN", includes = stdint)
	public static final int_least64_t INT64_LEAST_MIN = int64_t.INT64_MIN.cast(int_least64_t.class);

	@CCodeTemplate(Scalar)
	public int_least64_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int_least64_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int_least64_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final int_least64_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final int_least64_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final int_least64_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final int_least64_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final int_least64_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final int_least64_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int_least64_t add(@NotNull final int_least64_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int_least64_t subtract(@NotNull final int_least64_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int_least64_t multiply(@NotNull final int_least64_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int_least64_t divide(@NotNull final int_least64_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int_least64_t modulus(@NotNull final int_least64_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public int_least64_t negate()
	{
		return negateT();
	}

	@Override
	public int_least64_t and(@NotNull final int_least64_t right)
	{
		return super.and(right);
	}

	@Override
	public int_least64_t or(@NotNull final int_least64_t right)
	{
		return super.or(right);
	}
}

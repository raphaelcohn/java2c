package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int_least16_t", includes = stdint)
public final class int_least16_t extends AbstractScalar<int_least16_t> implements Equality<int_least16_t>, Comparison<int_least16_t>, SignedMathematics<int_least16_t>, BooleanAlgebra<int_least16_t>
{
	@CCodeTemplate(value = "INT16_LEAST_MAX", includes = stdint)
	public static final int_least16_t INT16_LEAST_MAX = int16_t.INT16_MAX.cast(int_least16_t.class);

	@CCodeTemplate(value = "INT16_LEAST_MIN", includes = stdint)
	public static final int_least16_t INT16_LEAST_MIN = int16_t.INT16_MIN.cast(int_least16_t.class);

	@CCodeTemplate(Scalar)
	public int_least16_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int_least16_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int_least16_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final int_least16_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final int_least16_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final int_least16_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final int_least16_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final int_least16_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final int_least16_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int_least16_t add(@NotNull final int_least16_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int_least16_t subtract(@NotNull final int_least16_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int_least16_t multiply(@NotNull final int_least16_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int_least16_t divide(@NotNull final int_least16_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int_least16_t modulus(@NotNull final int_least16_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public int_least16_t negate()
	{
		return negateT();
	}

	@Override
	public int_least16_t and(@NotNull final int_least16_t right)
	{
		return super.and(right);
	}

	@Override
	public int_least16_t or(@NotNull final int_least16_t right)
	{
		return super.or(right);
	}
}

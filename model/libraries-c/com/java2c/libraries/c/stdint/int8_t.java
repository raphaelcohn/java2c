package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.other.literal;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int8_t", includes = stdint)
public final class int8_t extends AbstractScalar<int8_t> implements Equality<int8_t>, Comparison<int8_t>, SignedMathematics<int8_t>, BooleanAlgebra<int8_t>
{
	@CCodeTemplate(value = "INT8_MAX", includes = stdint)
	public static final int8_t INT8_MAX = new int8_t(127L);

	@CCodeTemplate(value = "INT8_MIN", includes = stdint)
	public static final int8_t INT8_MIN = new int8_t(-128L);

	@CCodeTemplate(value = "INT8_C(value)", includes = stdint)
	public static int8_t literal(@literal final long value)
	{
		return new int8_t(value);
	}

	@CCodeTemplate(Scalar)
	public int8_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int8_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int8_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final int8_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final int8_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final int8_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final int8_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final int8_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final int8_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int8_t add(@NotNull final int8_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int8_t subtract(@NotNull final int8_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int8_t multiply(@NotNull final int8_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int8_t divide(@NotNull final int8_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int8_t modulus(@NotNull final int8_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public int8_t negate()
	{
		return negateT();
	}

	@Override
	public int8_t and(@NotNull final int8_t right)
	{
		return super.and(right);
	}

	@Override
	public int8_t or(@NotNull final int8_t right)
	{
		return super.or(right);
	}
}

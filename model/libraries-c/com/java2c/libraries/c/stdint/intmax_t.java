package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.other.literal;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;
import static com.java2c.libraries.c.stdint.int64_t.INT64_MAX;
import static com.java2c.libraries.c.stdint.int64_t.INT64_MIN;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "intmax_t", includes = stdint)
public final class intmax_t extends AbstractScalar<intmax_t> implements Equality<intmax_t>, Comparison<intmax_t>, SignedMathematics<intmax_t>, BooleanAlgebra<intmax_t>
{
	@CCodeTemplate(value = "INTMAX_MIN", includes = stdint)
	@NotNull
	public static final intmax_t INTMAX_MIN = INT64_MIN.cast(intmax_t.class);

	@CCodeTemplate(value = "INTMAX_MAX", includes = stdint)
	@NotNull
	public static final intmax_t INTMAX_MAX = INT64_MAX.cast(intmax_t.class);

	@CCodeTemplate(value = "INTMAX_C(value)", includes = stdint)
	@NotNull
	public static intmax_t literal(@literal final long value)
	{
		return new intmax_t(value);
	}

	@CCodeTemplate(Scalar)
	public intmax_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected intmax_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new intmax_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final intmax_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final intmax_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final intmax_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final intmax_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final intmax_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final intmax_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public intmax_t add(@NotNull final intmax_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public intmax_t subtract(@NotNull final intmax_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public intmax_t multiply(@NotNull final intmax_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public intmax_t divide(@NotNull final intmax_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public intmax_t modulus(@NotNull final intmax_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public intmax_t negate()
	{
		return negateT();
	}

	@Override
	public intmax_t and(@NotNull final intmax_t right)
	{
		return super.and(right);
	}

	@Override
	public intmax_t or(@NotNull final intmax_t right)
	{
		return super.or(right);
	}
}

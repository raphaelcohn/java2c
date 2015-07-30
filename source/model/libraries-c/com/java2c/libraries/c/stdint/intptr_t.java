package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;
import static com.java2c.libraries.c.stdint.int64_t.INT64_MIN;
import static com.java2c.libraries.c.stdint.int64_t.INT64_MAX;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "intptr_t", includes = stdint)
public final class intptr_t extends AbstractScalar<intptr_t> implements Equality<intptr_t>, Comparison<intptr_t>, SignedMathematics<intptr_t>, BooleanAlgebra<intptr_t>
{
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "INTPTR_MIN", includes = stdint)
	@NotNull
	public static final intptr_t INTPTR_MIN = INT64_MIN.cast(intptr_t.class);

	// Only on 64-bit is this valid
	@CCodeTemplate(value = "INTPTR_MAX", includes = stdint)
	@NotNull
	public static final intptr_t INTPTR_MAX = INT64_MAX.cast(intptr_t.class);

	@CCodeTemplate(Scalar)
	public intptr_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected intptr_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new intptr_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final intptr_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final intptr_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final intptr_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final intptr_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final intptr_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final intptr_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public intptr_t add(@NotNull final intptr_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public intptr_t subtract(@NotNull final intptr_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public intptr_t multiply(@NotNull final intptr_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public intptr_t divide(@NotNull final intptr_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public intptr_t modulus(@NotNull final intptr_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public intptr_t negate()
	{
		return negateT();
	}

	@Override
	public intptr_t and(@NotNull final intptr_t right)
	{
		return super.and(right);
	}

	@Override
	public intptr_t or(@NotNull final intptr_t right)
	{
		return super.or(right);
	}
}

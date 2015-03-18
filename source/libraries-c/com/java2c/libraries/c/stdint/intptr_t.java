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
import static com.java2c.libraries.c.stdint.int64_t.INT64_MIN;
import static com.java2c.libraries.c.stdint.int64_t.INT64_MAX;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "intptr_t", includes = stdint)
public final class intptr_t extends AbstractScalar<intptr_t> implements Equality<intptr_t>, Comparison<intptr_t>, Mathematics<intptr_t>, BooleanAlgebra<intptr_t>
{
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "INTPTR_MIN", includes = stdint)
	public static final intptr_t INTPTR_MIN = INT64_MIN.cast(intptr_t.class);

	// Only on 64-bit is this valid
	@CCodeTemplate(value = "INTPTR_MAX", includes = stdint)
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
	public boolean equals(@NotNull final intptr_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final intptr_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final intptr_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final intptr_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final intptr_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final intptr_t right)
	{
		return super.lessThanOrEqualTo(right);
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

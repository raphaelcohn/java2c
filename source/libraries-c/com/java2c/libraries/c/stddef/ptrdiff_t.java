package com.java2c.libraries.c.stddef;

import com.java2c.model.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.scalars.Scalar;
import com.java2c.model.scalars.BooleanAlgebra;
import com.java2c.model.scalars.Comparison;
import com.java2c.model.scalars.Equality;
import com.java2c.model.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.libraries.c.stdint.int64_t.INT64_MAX;
import static com.java2c.libraries.c.stdint.int64_t.INT64_MIN;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "ptrdiff_t", includes = stddef.stddef)
public final class ptrdiff_t extends AbstractScalar<ptrdiff_t> implements Equality<ptrdiff_t>, Comparison<ptrdiff_t>, Mathematics<ptrdiff_t>, BooleanAlgebra<ptrdiff_t>
{
	// Yes, the preprocessor constant is in a different header!
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "PTRDIFF_MIN", includes = stdint)
	public static final ptrdiff_t PTRDIFF_MIN = INT64_MIN.cast(ptrdiff_t.class);

	// Yes, the preprocessor constant is in a different header!
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "PTRDIFF_MAX", includes = stdint)
	public static final ptrdiff_t PTRDIFF_MAX = INT64_MAX.cast(ptrdiff_t.class);

	@CCodeTemplate(CCodeTemplate.Scalar)
	public ptrdiff_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected ptrdiff_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new ptrdiff_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equal(@NotNull final ptrdiff_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEqual(@NotNull final ptrdiff_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final ptrdiff_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final ptrdiff_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final ptrdiff_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final ptrdiff_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public ptrdiff_t add(@NotNull final ptrdiff_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public ptrdiff_t subtract(@NotNull final ptrdiff_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public ptrdiff_t multiply(@NotNull final ptrdiff_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public ptrdiff_t divide(@NotNull final ptrdiff_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public ptrdiff_t modulus(@NotNull final ptrdiff_t right)
	{
		return super.modulus(right);
	}

	@Override
	public ptrdiff_t and(@NotNull final ptrdiff_t right)
	{
		return super.and(right);
	}

	@Override
	public ptrdiff_t or(@NotNull final ptrdiff_t right)
	{
		return super.or(right);
	}
}

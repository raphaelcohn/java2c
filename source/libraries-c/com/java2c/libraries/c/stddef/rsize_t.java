package com.java2c.libraries.c.stddef;

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

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "rsize_t", signed = false, includes = stddef.stddef)
public final class rsize_t extends AbstractScalar<rsize_t> implements Equality<rsize_t>, Comparison<rsize_t>, Mathematics<rsize_t>, BooleanAlgebra<rsize_t>
{
	// Yes, the preprocessor constant is in a different header!
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "RSIZE_MAX", includes = stdint)
	public static final rsize_t RSIZE_MAX = new rsize_t(size_t.SIZE_MAX.value << 1);

	@CCodeTemplate(Scalar)
	public rsize_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected rsize_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new rsize_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final rsize_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final rsize_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final rsize_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final rsize_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final rsize_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final rsize_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public rsize_t add(@NotNull final rsize_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public rsize_t subtract(@NotNull final rsize_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public rsize_t multiply(@NotNull final rsize_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public rsize_t divide(@NotNull final rsize_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public rsize_t modulus(@NotNull final rsize_t right)
	{
		return super.modulus(right);
	}

	@Override
	public rsize_t and(@NotNull final rsize_t right)
	{
		return super.and(right);
	}

	@Override
	public rsize_t or(@NotNull final rsize_t right)
	{
		return super.or(right);
	}
}

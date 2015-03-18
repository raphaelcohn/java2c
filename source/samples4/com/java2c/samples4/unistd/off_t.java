package com.java2c.samples4.unistd;

import com.java2c.samples4.AbstractScalar;
import com.java2c.samples4.CCodeTemplate;
import com.java2c.samples4.Scalar;
import com.java2c.samples4.scalars.BooleanAlgebra;
import com.java2c.samples4.scalars.Comparison;
import com.java2c.samples4.scalars.Equality;
import com.java2c.samples4.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.samples4.unistd.unistd.unistd;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "off_t", includes = unistd)
public final class off_t extends AbstractScalar<off_t> implements Equality<off_t>, Comparison<off_t>, Mathematics<off_t>, BooleanAlgebra<off_t>
{
	@CCodeTemplate(CCodeTemplate.Scalar)
	public off_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected off_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new off_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equals(@NotNull final off_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEquals(@NotNull final off_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final off_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final off_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final off_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final off_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public off_t add(@NotNull final off_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public off_t subtract(@NotNull final off_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public off_t multiply(@NotNull final off_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public off_t divide(@NotNull final off_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public off_t modulus(@NotNull final off_t right)
	{
		return super.modulus(right);
	}

	@Override
	public off_t and(@NotNull final off_t right)
	{
		return super.and(right);
	}

	@Override
	public off_t or(@NotNull final off_t right)
	{
		return super.or(right);
	}
}

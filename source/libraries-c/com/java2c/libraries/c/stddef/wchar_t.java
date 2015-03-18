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
@Scalar(value = "wchar_t", includes = stddef.stddef)
public final class wchar_t extends AbstractScalar<wchar_t> implements Equality<wchar_t>, Comparison<wchar_t>, Mathematics<wchar_t>, BooleanAlgebra<wchar_t>
{
	// Yes, the preprocessor constant is in a different header!
	// Only if wchar_t is defined unsigned
	@CCodeTemplate(value = "WCHAR_MIN", includes = stdint)
	public static final wchar_t WCHAR_MIN = new wchar_t(0x7fffffffL);

	// Yes, the preprocessor constant is in a different header!
	// Only if wchar_t is defined unsigned
	@CCodeTemplate(value = "WCHAR_MAX", includes = stdint)
	public static final wchar_t WCHAR_MAX = new wchar_t(0L);

	@CCodeTemplate(Scalar)
	public wchar_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected wchar_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new wchar_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean equal(@NotNull final wchar_t right)
	{
		return super.equals(right);
	}

	@Override
	public boolean notEqual(@NotNull final wchar_t right)
	{
		return super.notEquals(right);
	}

	@Override
	public boolean greaterThan(@NotNull final wchar_t right)
	{
		return super.greaterThan(right);
	}

	@Override
	public boolean lessThan(@NotNull final wchar_t right)
	{
		return super.lessThan(right);
	}

	@Override
	public boolean greaterThanOrEqualTo(@NotNull final wchar_t right)
	{
		return super.greaterThanOrEqualTo(right);
	}

	@Override
	public boolean lessThanOrEqualTo(@NotNull final wchar_t right)
	{
		return super.lessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public wchar_t add(@NotNull final wchar_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public wchar_t subtract(@NotNull final wchar_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public wchar_t multiply(@NotNull final wchar_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public wchar_t divide(@NotNull final wchar_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public wchar_t modulus(@NotNull final wchar_t right)
	{
		return super.modulus(right);
	}

	@Override
	public wchar_t and(@NotNull final wchar_t right)
	{
		return super.and(right);
	}

	@Override
	public wchar_t or(@NotNull final wchar_t right)
	{
		return super.or(right);
	}
}

package com.java2c.libraries.c.stddef;

import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.model.types.scalars.BooleanAlgebra;
import com.java2c.model.types.scalars.Comparison;
import com.java2c.model.types.scalars.Equality;
import com.java2c.model.types.scalars.Mathematics;
import com.java2c.libraries.c.stdint.uint64_t;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "size_t", signed = false, includes = stddef.stddef)
public final class size_t extends AbstractScalar<size_t> implements Equality<size_t>, Comparison<size_t>, Mathematics<size_t>, BooleanAlgebra<size_t>
{
	// Yes, the preprocessor constant is in a different header!
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "SIZE_MAX", includes = stdint)
	public static final size_t SIZE_MAX = uint64_t.UINT64_MAX.cast(size_t.class);

	@CCodeTemplate(Scalar)
	public size_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected size_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new size_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final size_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final size_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final size_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final size_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final size_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final size_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public size_t add(@NotNull final size_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public size_t subtract(@NotNull final size_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public size_t multiply(@NotNull final size_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public size_t divide(@NotNull final size_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public size_t modulus(@NotNull final size_t right)
	{
		return super.modulus(right);
	}

	@Override
	public size_t and(@NotNull final size_t right)
	{
		return super.and(right);
	}

	@Override
	public size_t or(@NotNull final size_t right)
	{
		return super.or(right);
	}
}

package com.java2c.libraries.builtin;

import com.java2c.model.other.literal;
import com.java2c.model.types.Banished;
import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;

// uint8_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar("signed char")
public final class signed_char extends AbstractScalar<signed_char> implements Equality<signed_char>, Comparison<signed_char>, SignedMathematics<signed_char>, BooleanAlgebra<signed_char>
{
	@CCodeTemplate(Scalar)
	public signed_char(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected signed_char constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new signed_char(value);
	}

	@Override
	public boolean isEqual(@NotNull final signed_char right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final signed_char right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final signed_char right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final signed_char right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final signed_char right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final signed_char right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public signed_char add(@NotNull final signed_char right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public signed_char subtract(@NotNull final signed_char right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public signed_char multiply(@NotNull final signed_char right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public signed_char divide(@NotNull final signed_char right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public signed_char modulus(@NotNull final signed_char right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public signed_char negate()
	{
		return negateT();
	}

	@Override
	public signed_char and(@NotNull final signed_char right)
	{
		return super.and(right);
	}

	@Override
	public signed_char or(@NotNull final signed_char right)
	{
		return super.or(right);
	}
}

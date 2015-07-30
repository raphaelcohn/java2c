/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.builtin;

import com.java2c.model.other.literal;
import com.java2c.model.types.Banished;
import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;

// int16_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar("signed short")
public final class signed_short extends AbstractScalar<signed_short> implements Equality<signed_short>, Comparison<signed_short>, SignedMathematics<signed_short>, BooleanAlgebra<signed_short>
{
	@CCodeTemplate(Scalar)
	public signed_short(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected signed_short constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new signed_short(value);
	}

	@Override
	public boolean isEqual(@NotNull final signed_short right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final signed_short right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final signed_short right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final signed_short right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final signed_short right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final signed_short right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public signed_short add(@NotNull final signed_short right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public signed_short subtract(@NotNull final signed_short right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public signed_short multiply(@NotNull final signed_short right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public signed_short divide(@NotNull final signed_short right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public signed_short modulus(@NotNull final signed_short right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public signed_short negate()
	{
		return negateT();
	}

	@Override
	public signed_short and(@NotNull final signed_short right)
	{
		return super.and(right);
	}

	@Override
	public signed_short or(@NotNull final signed_short right)
	{
		return super.or(right);
	}
}

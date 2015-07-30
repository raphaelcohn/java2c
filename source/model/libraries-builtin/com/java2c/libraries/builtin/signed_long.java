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

@SuppressWarnings("ClassNamingConvention")
@Scalar("signed long")
public final class signed_long extends AbstractScalar<signed_long> implements Equality<signed_long>, Comparison<signed_long>, SignedMathematics<signed_long>, BooleanAlgebra<signed_long>
{
	@CCodeTemplate(Scalar)
	public signed_long(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected signed_long constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new signed_long(value);
	}

	@Override
	public boolean isEqual(@NotNull final signed_long right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final signed_long right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final signed_long right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final signed_long right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final signed_long right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final signed_long right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public signed_long add(@NotNull final signed_long right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public signed_long subtract(@NotNull final signed_long right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public signed_long multiply(@NotNull final signed_long right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public signed_long divide(@NotNull final signed_long right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public signed_long modulus(@NotNull final signed_long right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public signed_long negate()
	{
		return negateT();
	}

	@Override
	public signed_long and(@NotNull final signed_long right)
	{
		return super.and(right);
	}

	@Override
	public signed_long or(@NotNull final signed_long right)
	{
		return super.or(right);
	}
}

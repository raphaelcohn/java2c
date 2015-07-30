/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.builtin;

import com.java2c.model.other.literal;
import com.java2c.model.types.Banished;
import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.model.types.scalars.BooleanAlgebra;
import com.java2c.model.types.scalars.Comparison;
import com.java2c.model.types.scalars.Equality;
import com.java2c.model.types.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;

// uint8_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned char", signed = false)
public final class unsigned_char extends AbstractScalar<unsigned_char> implements Equality<unsigned_char>, Comparison<unsigned_char>, Mathematics<unsigned_char>, BooleanAlgebra<unsigned_char>
{
	@CCodeTemplate(Scalar)
	public unsigned_char(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected unsigned_char constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new unsigned_char(value);
	}

	@Override
	public boolean isEqual(@NotNull final unsigned_char right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final unsigned_char right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final unsigned_char right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final unsigned_char right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final unsigned_char right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final unsigned_char right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public unsigned_char add(@NotNull final unsigned_char right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public unsigned_char subtract(@NotNull final unsigned_char right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public unsigned_char multiply(@NotNull final unsigned_char right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public unsigned_char divide(@NotNull final unsigned_char right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public unsigned_char modulus(@NotNull final unsigned_char right)
	{
		return super.modulus(right);
	}

	@Override
	public unsigned_char and(@NotNull final unsigned_char right)
	{
		return super.and(right);
	}

	@Override
	public unsigned_char or(@NotNull final unsigned_char right)
	{
		return super.or(right);
	}
}

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

// uint64_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned long long", signed = false)
public final class unsigned_long_long extends AbstractScalar<unsigned_long_long> implements Equality<unsigned_long_long>, Comparison<unsigned_long_long>, Mathematics<unsigned_long_long>, BooleanAlgebra<unsigned_long_long>
{
	@CCodeTemplate(Scalar)
	public unsigned_long_long(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected unsigned_long_long constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new unsigned_long_long(value);
	}

	@Override
	public boolean isEqual(@NotNull final unsigned_long_long right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final unsigned_long_long right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final unsigned_long_long right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final unsigned_long_long right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final unsigned_long_long right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final unsigned_long_long right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public unsigned_long_long add(@NotNull final unsigned_long_long right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public unsigned_long_long subtract(@NotNull final unsigned_long_long right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public unsigned_long_long multiply(@NotNull final unsigned_long_long right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public unsigned_long_long divide(@NotNull final unsigned_long_long right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public unsigned_long_long modulus(@NotNull final unsigned_long_long right)
	{
		return super.modulus(right);
	}

	@Override
	public unsigned_long_long and(@NotNull final unsigned_long_long right)
	{
		return super.and(right);
	}

	@Override
	public unsigned_long_long or(@NotNull final unsigned_long_long right)
	{
		return super.or(right);
	}
}

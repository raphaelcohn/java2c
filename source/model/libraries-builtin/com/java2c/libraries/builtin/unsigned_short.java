/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.builtin;

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

// uint16_t on LP64
@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "unsigned short", signed = false)
public final class unsigned_short extends AbstractScalar<unsigned_short> implements Equality<unsigned_short>, Comparison<unsigned_short>, Mathematics<unsigned_short>, BooleanAlgebra<unsigned_short>
{
	@CCodeTemplate(Scalar)
	public unsigned_short(final long value)
	{
		super(value);
	}
	@SuppressWarnings("CovariantEquals")

	@Banished
	@NotNull
	@Override
	protected unsigned_short constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new unsigned_short(value);
	}

	@Override
	public boolean isEqual(@NotNull final unsigned_short right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final unsigned_short right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final unsigned_short right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final unsigned_short right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final unsigned_short right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final unsigned_short right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public unsigned_short add(@NotNull final unsigned_short right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public unsigned_short subtract(@NotNull final unsigned_short right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public unsigned_short multiply(@NotNull final unsigned_short right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public unsigned_short divide(@NotNull final unsigned_short right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public unsigned_short modulus(@NotNull final unsigned_short right)
	{
		return super.modulus(right);
	}

	@Override
	public unsigned_short and(@NotNull final unsigned_short right)
	{
		return super.and(right);
	}

	@Override
	public unsigned_short or(@NotNull final unsigned_short right)
	{
		return super.or(right);
	}
}

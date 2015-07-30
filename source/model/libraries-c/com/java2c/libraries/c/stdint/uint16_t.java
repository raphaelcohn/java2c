/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.model.other.literal;
import com.java2c.model.types.scalars.BooleanAlgebra;
import com.java2c.model.types.scalars.Comparison;
import com.java2c.model.types.scalars.Equality;
import com.java2c.model.types.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "uint16_t", signed = false, includes = stdint)
public final class uint16_t extends AbstractScalar<uint16_t> implements Equality<uint16_t>, Comparison<uint16_t>, Mathematics<uint16_t>, BooleanAlgebra<uint16_t>
{
	@CCodeTemplate(value = "UINT16_MAX", includes = stdint)
	public static final uint16_t UINT16_MAX = new uint16_t(65535L);

	@CCodeTemplate(value = "UINT16_C(value)", includes = stdint)
	public static uint16_t literal(@literal final long value)
	{
		return new uint16_t(value);
	}

	@CCodeTemplate(Scalar)
	public uint16_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint16_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint16_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final uint16_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final uint16_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final uint16_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final uint16_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final uint16_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final uint16_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint16_t add(@NotNull final uint16_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint16_t subtract(@NotNull final uint16_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint16_t multiply(@NotNull final uint16_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint16_t divide(@NotNull final uint16_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint16_t modulus(@NotNull final uint16_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint16_t and(@NotNull final uint16_t right)
	{
		return super.and(right);
	}

	@Override
	public uint16_t or(@NotNull final uint16_t right)
	{
		return super.or(right);
	}
}

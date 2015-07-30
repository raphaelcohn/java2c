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
@Scalar(value = "uint64_t", signed = false, includes = stdint)
public final class uint64_t extends AbstractScalar<uint64_t> implements Equality<uint64_t>, Comparison<uint64_t>, Mathematics<uint64_t>, BooleanAlgebra<uint64_t>
{
	@CCodeTemplate(value = "UINT64_MAX", includes = stdint)
	@NotNull
	public static final uint64_t UINT64_MAX = new uint64_t(0xFFFFFFFFFFFFFFFFL);

	@CCodeTemplate(value = "UINT64_C(value)", includes = stdint)
	@NotNull
	public static uint64_t literal(@literal final long value)
	{
		return new uint64_t(value);
	}

	@CCodeTemplate(Scalar)
	public uint64_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint64_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint64_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final uint64_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final uint64_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final uint64_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final uint64_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final uint64_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final uint64_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint64_t add(@NotNull final uint64_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint64_t subtract(@NotNull final uint64_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint64_t multiply(@NotNull final uint64_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint64_t divide(@NotNull final uint64_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint64_t modulus(@NotNull final uint64_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint64_t and(@NotNull final uint64_t right)
	{
		return super.and(right);
	}

	@Override
	public uint64_t or(@NotNull final uint64_t right)
	{
		return super.or(right);
	}
}

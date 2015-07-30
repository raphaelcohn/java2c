/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.model.types.scalars.BooleanAlgebra;
import com.java2c.model.types.scalars.Comparison;
import com.java2c.model.types.scalars.Equality;
import com.java2c.model.types.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "uint_least16_t", signed = false, includes = stdint)
public final class uint_least16_t extends AbstractScalar<uint_least16_t> implements Equality<uint_least16_t>, Comparison<uint_least16_t>, Mathematics<uint_least16_t>, BooleanAlgebra<uint_least16_t>
{
	@CCodeTemplate(value = "UINT16_LEAST_MAX", includes = stdint)
	public static final uint_least16_t UINT16_LEAST_MAX = uint16_t.UINT16_MAX.cast(uint_least16_t.class);

	@CCodeTemplate(Scalar)
	public uint_least16_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected uint_least16_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new uint_least16_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final uint_least16_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final uint_least16_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final uint_least16_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final uint_least16_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final uint_least16_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final uint_least16_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public uint_least16_t add(@NotNull final uint_least16_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public uint_least16_t subtract(@NotNull final uint_least16_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public uint_least16_t multiply(@NotNull final uint_least16_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public uint_least16_t divide(@NotNull final uint_least16_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public uint_least16_t modulus(@NotNull final uint_least16_t right)
	{
		return super.modulus(right);
	}

	@Override
	public uint_least16_t and(@NotNull final uint_least16_t right)
	{
		return super.and(right);
	}

	@Override
	public uint_least16_t or(@NotNull final uint_least16_t right)
	{
		return super.or(right);
	}
}

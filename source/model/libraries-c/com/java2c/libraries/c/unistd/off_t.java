/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.c.unistd;

import com.java2c.model.types.scalars.AbstractScalar;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.scalars.Scalar;
import com.java2c.model.types.scalars.BooleanAlgebra;
import com.java2c.model.types.scalars.Comparison;
import com.java2c.model.types.scalars.Equality;
import com.java2c.model.types.scalars.Mathematics;
import org.jetbrains.annotations.NotNull;

import static com.java2c.libraries.c.unistd.unistd.unistd;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "off_t", includes = unistd)
public final class off_t extends AbstractScalar<off_t> implements Equality<off_t>, Comparison<off_t>, Mathematics<off_t>, BooleanAlgebra<off_t>
{
	@CCodeTemplate(CCodeTemplate.Scalar)
	public off_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected off_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new off_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final off_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final off_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final off_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final off_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final off_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final off_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public off_t add(@NotNull final off_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public off_t subtract(@NotNull final off_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public off_t multiply(@NotNull final off_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public off_t divide(@NotNull final off_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public off_t modulus(@NotNull final off_t right)
	{
		return super.modulus(right);
	}

	@Override
	public off_t and(@NotNull final off_t right)
	{
		return super.and(right);
	}

	@Override
	public off_t or(@NotNull final off_t right)
	{
		return super.or(right);
	}
}

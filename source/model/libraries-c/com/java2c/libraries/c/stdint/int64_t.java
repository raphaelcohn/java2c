/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.c.stdint;

import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.other.literal;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "int64_t", includes = stdint)
public final class int64_t extends AbstractScalar<int64_t> implements Equality<int64_t>, Comparison<int64_t>, SignedMathematics<int64_t>, BooleanAlgebra<int64_t>
{
	@CCodeTemplate(value = "INT64_MAX", includes = stdint)
	@NotNull
	public static final int64_t INT64_MAX = new int64_t(9223372036854775807L);

	@CCodeTemplate(value = "INT64_MIN", includes = stdint)
	@NotNull
	public static final int64_t INT64_MIN = new int64_t(INT64_MAX.negateT().value() - 1L);

	@CCodeTemplate(value = "INT64_C(value)", includes = stdint)
	@NotNull
	public static int64_t literal(@literal final long value)
	{
		return new int64_t(value);
	}

	@CCodeTemplate(Scalar)
	public int64_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int64_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int64_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final int64_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final int64_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final int64_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final int64_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final int64_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final int64_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int64_t add(@NotNull final int64_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int64_t subtract(@NotNull final int64_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int64_t multiply(@NotNull final int64_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int64_t divide(@NotNull final int64_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int64_t modulus(@NotNull final int64_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public int64_t negate()
	{
		return negateT();
	}

	@Override
	public int64_t and(@NotNull final int64_t right)
	{
		return super.and(right);
	}

	@Override
	public int64_t or(@NotNull final int64_t right)
	{
		return super.or(right);
	}
}

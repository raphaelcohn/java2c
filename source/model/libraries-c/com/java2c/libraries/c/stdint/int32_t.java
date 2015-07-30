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
@Scalar(value = "int32_t", includes = stdint)
public final class int32_t extends AbstractScalar<int32_t> implements Equality<int32_t>, Comparison<int32_t>, SignedMathematics<int32_t>, BooleanAlgebra<int32_t>
{
	@CCodeTemplate(value = "INT32_MAX", includes = stdint)
	@NotNull
	public static final int32_t INT32_MAX = new int32_t(2147483647L);

	@CCodeTemplate(value = "INT32_MIN", includes = stdint)
	@NotNull
	public static final int32_t INT32_MIN = new int32_t(INT32_MAX.negateT().value() - 1L);

	@CCodeTemplate(value = "INT32_C(value)", includes = stdint)
	@NotNull
	public static int32_t literal(@literal final long value)
	{
		return new int32_t(value);
	}

	@CCodeTemplate(Scalar)
	public int32_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected int32_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new int32_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final int32_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final int32_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final int32_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final int32_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final int32_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final int32_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public int32_t add(@NotNull final int32_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public int32_t subtract(@NotNull final int32_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public int32_t multiply(@NotNull final int32_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public int32_t divide(@NotNull final int32_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public int32_t modulus(@NotNull final int32_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public int32_t negate()
	{
		return negateT();
	}

	@Override
	public int32_t and(@NotNull final int32_t right)
	{
		return super.and(right);
	}

	@Override
	public int32_t or(@NotNull final int32_t right)
	{
		return super.or(right);
	}
}

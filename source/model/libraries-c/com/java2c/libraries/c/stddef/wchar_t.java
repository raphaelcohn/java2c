/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.c.stddef;

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
@Scalar(value = "wchar_t", includes = stddef.stddef)
public final class wchar_t extends AbstractScalar<wchar_t> implements Equality<wchar_t>, Comparison<wchar_t>, Mathematics<wchar_t>, BooleanAlgebra<wchar_t>
{
	// Yes, the preprocessor constant is in a different header!
	// Only if wchar_t is defined unsigned
	@CCodeTemplate(value = "WCHAR_MIN", includes = stdint)
	public static final wchar_t WCHAR_MIN = new wchar_t(0x7fffffffL);

	// Yes, the preprocessor constant is in a different header!
	// Only if wchar_t is defined unsigned
	@CCodeTemplate(value = "WCHAR_MAX", includes = stdint)
	public static final wchar_t WCHAR_MAX = new wchar_t(0L);

	@CCodeTemplate(Scalar)
	public wchar_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected wchar_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new wchar_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final wchar_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final wchar_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final wchar_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final wchar_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final wchar_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final wchar_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public wchar_t add(@NotNull final wchar_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public wchar_t subtract(@NotNull final wchar_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public wchar_t multiply(@NotNull final wchar_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public wchar_t divide(@NotNull final wchar_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public wchar_t modulus(@NotNull final wchar_t right)
	{
		return super.modulus(right);
	}

	@Override
	public wchar_t and(@NotNull final wchar_t right)
	{
		return super.and(right);
	}

	@Override
	public wchar_t or(@NotNull final wchar_t right)
	{
		return super.or(right);
	}
}

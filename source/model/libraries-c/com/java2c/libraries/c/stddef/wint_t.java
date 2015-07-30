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
import com.java2c.libraries.c.stdint.int32_t;
import org.jetbrains.annotations.NotNull;

import static com.java2c.model.other.CCodeTemplate.Scalar;
import static com.java2c.libraries.c.stdint.stdint.stdint;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "wint_t", includes = stddef.stddef)
public final class wint_t extends AbstractScalar<wint_t> implements Equality<wint_t>, Comparison<wint_t>, Mathematics<wint_t>, BooleanAlgebra<wint_t>
{
	// Yes, the preprocessor constant is in a different header!
	@CCodeTemplate(value = "WINT_MIN", includes = stdint)
	public static final wint_t WINT_MIN = int32_t.INT32_MIN.cast(wint_t.class);

	// Yes, the preprocessor constant is in a different header!
	@CCodeTemplate(value = "WINT_MAX", includes = stdint)
	public static final wint_t WINT_MAX = int32_t.INT32_MAX.cast(wint_t.class);

	@CCodeTemplate(Scalar)
	public wint_t(final long value)
	{
		super(value);
	}

	@NotNull
	@Override
	protected wint_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") final long value)
	{
		return new wint_t(value);
	}

	@SuppressWarnings("CovariantEquals")
	@Override
	public boolean isEqual(@NotNull final wint_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final wint_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final wint_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final wint_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final wint_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final wint_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public wint_t add(@NotNull final wint_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public wint_t subtract(@NotNull final wint_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public wint_t multiply(@NotNull final wint_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public wint_t divide(@NotNull final wint_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public wint_t modulus(@NotNull final wint_t right)
	{
		return super.modulus(right);
	}

	@Override
	public wint_t and(@NotNull final wint_t right)
	{
		return super.and(right);
	}

	@Override
	public wint_t or(@NotNull final wint_t right)
	{
		return super.or(right);
	}
}

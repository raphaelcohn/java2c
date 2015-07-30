/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.c.stddef;

import com.java2c.model.other.literal;
import com.java2c.model.types.Banished;
import com.java2c.model.types.scalars.*;
import com.java2c.model.other.CCodeTemplate;
import org.jetbrains.annotations.NotNull;

import static com.java2c.libraries.c.stdint.int64_t.INT64_MAX;
import static com.java2c.libraries.c.stdint.int64_t.INT64_MIN;
import static com.java2c.libraries.c.stdint.stdint.stdint;
import static com.java2c.model.other.CCodeTemplate.Scalar;

@SuppressWarnings("ClassNamingConvention")
@Scalar(value = "ptrdiff_t", includes = stddef.stddef)
public final class ptrdiff_t extends AbstractScalar<ptrdiff_t> implements Equality<ptrdiff_t>, Comparison<ptrdiff_t>, SignedMathematics<ptrdiff_t>, BooleanAlgebra<ptrdiff_t>
{
	// Yes, the preprocessor constant is in a different header!
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "PTRDIFF_MIN", includes = stdint)
	public static final ptrdiff_t PTRDIFF_MIN = INT64_MIN.cast(ptrdiff_t.class);

	// Yes, the preprocessor constant is in a different header!
	// Only on 64-bit is this valid
	@CCodeTemplate(value = "PTRDIFF_MAX", includes = stdint)
	public static final ptrdiff_t PTRDIFF_MAX = INT64_MAX.cast(ptrdiff_t.class);

	@CCodeTemplate(Scalar)
	public ptrdiff_t(@literal final long value)
	{
		super(value);
	}

	@Banished
	@NotNull
	@Override
	protected ptrdiff_t constructT(@SuppressWarnings("ParameterHidesMemberVariable") @literal final long value)
	{
		return new ptrdiff_t(value);
	}

	@Override
	public boolean isEqual(@NotNull final ptrdiff_t right)
	{
		return super.isEqual(right);
	}

	@Override
	public boolean isNotEqual(@NotNull final ptrdiff_t right)
	{
		return super.isNotEqual(right);
	}

	@Override
	public boolean isGreaterThan(@NotNull final ptrdiff_t right)
	{
		return super.isGreaterThan(right);
	}

	@Override
	public boolean isLessThan(@NotNull final ptrdiff_t right)
	{
		return super.isLessThan(right);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@NotNull final ptrdiff_t right)
	{
		return super.isGreaterThanOrEqualTo(right);
	}

	@Override
	public boolean isLessThanOrEqualTo(@NotNull final ptrdiff_t right)
	{
		return super.isLessThanOrEqualTo(right);
	}

	@NotNull
	@Override
	public ptrdiff_t add(@NotNull final ptrdiff_t right)
	{
		return super.add(right);
	}

	@NotNull
	@Override
	public ptrdiff_t subtract(@NotNull final ptrdiff_t right)
	{
		return super.subtract(right);
	}

	@NotNull
	@Override
	public ptrdiff_t multiply(@NotNull final ptrdiff_t right)
	{
		return super.multiply(right);
	}

	@NotNull
	@Override
	public ptrdiff_t divide(@NotNull final ptrdiff_t right)
	{
		return super.divide(right);
	}

	@NotNull
	@Override
	public ptrdiff_t modulus(@NotNull final ptrdiff_t right)
	{
		return super.modulus(right);
	}

	@Override
	@NotNull
	public ptrdiff_t negate()
	{
		return negateT();
	}

	@Override
	public ptrdiff_t and(@NotNull final ptrdiff_t right)
	{
		return super.and(right);
	}

	@Override
	public ptrdiff_t or(@NotNull final ptrdiff_t right)
	{
		return super.or(right);
	}
}

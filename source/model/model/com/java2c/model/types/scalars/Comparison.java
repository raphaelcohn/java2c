/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.model.types.scalars;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.types.CType;
import com.java2c.model.types.Uncastable;
import org.jetbrains.annotations.NotNull;

@Uncastable
public interface Comparison<T> extends CType
{
	@CCodeTemplate("(@this@ > @right@)")
	boolean isGreaterThan(@NotNull final T right);

	@CCodeTemplate("(@this@ < @right@)")
	boolean isLessThan(@NotNull final T right);

	@CCodeTemplate("(@this@ >= @right@)")
	boolean isGreaterThanOrEqualTo(@NotNull final T right);

	@CCodeTemplate("(@this@ <= @right@)")
	boolean isLessThanOrEqualTo(@NotNull final T right);
}

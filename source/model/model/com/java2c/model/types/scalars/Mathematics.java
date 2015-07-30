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
public interface Mathematics<T> extends CType
{
	@CCodeTemplate("(@this@ + @right@)")
	@NotNull
	T add(@NotNull final T right);

	@CCodeTemplate("(@this@ - @right@)")
	@NotNull
	T subtract(@NotNull final T right);

	@CCodeTemplate("(@this@ * @right@)")
	@NotNull
	T multiply(@NotNull final T right);

	@SuppressWarnings("HardcodedFileSeparator")
	@CCodeTemplate("(@this@ / @right@)")
	@NotNull
	T divide(@NotNull final T right);

	@CCodeTemplate("(@this@ % @right@)")
	@NotNull
	T modulus(@NotNull final T right);
}

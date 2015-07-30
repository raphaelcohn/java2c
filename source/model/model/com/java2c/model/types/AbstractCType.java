/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.model.types;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("AbstractClassWithoutAbstractMethods")
@Uncastable
public abstract class AbstractCType implements CType
{
	protected AbstractCType()
	{
	}

	@Banished
	@Override
	public final boolean equals(@Nullable final Object obj) throws BanishedException
	{
		throw new UnsupportedOperationException("equals(Object obj) is Banished");
	}

	@Banished
	@Override
	public final int hashCode() throws BanishedException
	{
		throw new UnsupportedOperationException("hashCode is not supported for AbstractScalar");
	}

	@Banished
	@Override
	public final String toString() throws BanishedException
	{
		throw new BanishedException("toString()");
	}

	@Banished
	@Override
	protected final void finalize() throws BanishedException
	{
	}

	@Banished
	@NotNull
	@SuppressWarnings({"CloneInNonCloneableClass", "CloneDoesntCallSuperClone"})
	@Override
	protected final Object clone() throws CloneNotSupportedException, BanishedException
	{
		throw new BanishedException("clone()");
	}
}

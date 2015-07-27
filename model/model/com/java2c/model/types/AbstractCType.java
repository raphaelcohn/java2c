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

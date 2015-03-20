package com.java2c.model.types;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
@Uncastable
public interface CType
{
	@Banished
	@Override
	boolean equals(@Nullable final Object obj) throws BanishedException;

	@Banished
	@Override
	int hashCode() throws BanishedException;

	@Banished
	@Override
	String toString() throws BanishedException;
}

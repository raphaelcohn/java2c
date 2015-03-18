package com.java2c.model;

import org.jetbrains.annotations.NotNull;

abstract class Root
{
	protected Root()
	{
	}

	{

	}

	@Override
	@NotNull
	@Deprecated
	public String toString()
	{
		// Unfortunately, Java strings are big-endian UTF-16 with no final \0 (although this could be faked by us)
		// They also have a length (which can fake, but will faill if data contains an embedded \0)
		// On Windows, wchar_t is UTF-16; on Linux, it's UTF-32
		throw new NoSuchMethodError("It is illegal to call toString");
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	@Override
	@NotNull
	@Deprecated
	protected final Object clone() throws CloneNotSupportedException
	{
		throw new NoSuchMethodError("It is illegal to call clone");
	}

	@SuppressWarnings("FinalizeDoesntCallSuperFinalize")
	@Override
	@Deprecated
	protected final void finalize() throws Throwable
	{
		throw new NoSuchMethodError("It is illegal to call finalize");
	}
}

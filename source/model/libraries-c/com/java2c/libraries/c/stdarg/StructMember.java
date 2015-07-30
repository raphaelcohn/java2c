/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.libraries.c.stdarg;

import com.java2c.model.other.literal;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StructMember
{
	@SuppressWarnings("MethodNamesDifferingOnlyByCase")
	@NotNull
	public static StructMember structMember(@NotNull @literal final String structMember)
	{
		return new StructMember(structMember);
	}

	@NonNls @NotNull private final String structMember;

	private StructMember(@NotNull final String structMember)
	{
		this.structMember = structMember;
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}

		final StructMember that = (StructMember) obj;

		return structMember.equals(that.structMember);

	}

	@Override
	public int hashCode()
	{
		return structMember.hashCode();
	}

	@Override
	public String toString()
	{
		return structMember;
	}
}

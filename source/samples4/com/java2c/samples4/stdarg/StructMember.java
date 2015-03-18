package com.java2c.samples4.stdarg;

import com.java2c.samples4.literal;
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

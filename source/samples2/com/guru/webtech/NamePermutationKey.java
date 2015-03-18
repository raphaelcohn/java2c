package com.guru.webtech;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NamePermutationKey
{
	@NotNull
	public static NamePermutationKey namePermutationKey(@NonNls @NotNull final String subName)
	{
		return new NamePermutationKey(subName.toCharArray(), subName.length());
	}

	@NotNull private final char[] name;
	private final int subLength;

	@SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
	public NamePermutationKey(@NotNull final char[] name, final int subLength)
	{
		if (name.length < subLength)
		{
			throw new IllegalArgumentException("subLength can not exceed name length");
		}
		this.name = name;
		this.subLength = subLength;
	}

	@Override
	@NotNull
	public String toString()
	{
		return new String(name, 0, subLength);
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

		final NamePermutationKey that = (NamePermutationKey) obj;

		//noinspection SimplifiableIfStatement
		if (subLength != that.subLength)
		{
			return false;
		}

		for(int index = 0; index < subLength; index++)
		{
			if (name[index] != that.name[index])
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int arrResult = 1;
		for (int index = 0; index < subLength; index++)
		{
			final char element = name[index];
			arrResult = 31 * arrResult + (int) element;
		}

		int result = arrResult;
		result = 31 * result + subLength;
		return result;
	}
}

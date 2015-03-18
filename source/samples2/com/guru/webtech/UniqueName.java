package com.guru.webtech;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class UniqueName
{
	@NotNull
	public static UniqueName uniqueName(@NotNull final String name)
	{
		return new UniqueName(name.toCharArray());
	}

	@NotNull private final char[] value;

	@SuppressWarnings({"AssignmentToCollectionOrArrayFieldFromParameter", "MethodCanBeVariableArityMethod"})
	public UniqueName(@NotNull final char[] value)
	{
		if (value.length == 0)
		{
			throw new IllegalArgumentException("value must not be empty");
		}
		this.value = value;
	}

	@Override
	@NotNull
	public String toString()
	{
		return new String(value);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@NotNull
	public List<NamePermutationKey> generateNamePermutations()
	{
		final int length = value.length;
		final List<NamePermutationKey> namePermutationKeys = new ArrayList<>(length - 1);
		for(int index = 1; index <= length; index++)
		{
			namePermutationKeys.add(new NamePermutationKey(value, index));
		}
		return namePermutationKeys;
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

		final UniqueName that = (UniqueName) obj;

		return Arrays.equals(value, that.value);
	}

	@Override
	public int hashCode()
	{
		return Arrays.hashCode(value);
	}
}

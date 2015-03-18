package com.guru.webtech;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static com.guru.webtech.UniqueName.uniqueName;
import static java.util.Collections.emptySet;

public final class NamePermutationMultiMap
{
	@NotNull private static final Set<UniqueName> NoUniqueNames = emptySet();

	@NotNull private final Map<NamePermutationKey, Set<UniqueName>> multiMap;

	public NamePermutationMultiMap()
	{
		multiMap = new HashMap<>(16);
	}

	public void addNewUniqueNames(@NotNull final String... actualNames)
	{
		for (final String actualName : actualNames)
		{
			addANewUniqueName(actualName);
		}
	}

	public void addANewUniqueName(@NotNull final String actualName)
	{
		final UniqueName name = uniqueName(actualName);
		final List<NamePermutationKey> namePermutationKeys = name.generateNamePermutations();
		for (final NamePermutationKey namePermutationKey : namePermutationKeys)
		{
			final Set<UniqueName> uniqueNames;
			if (multiMap.containsKey(namePermutationKey))
			{
				uniqueNames = multiMap.get(namePermutationKey);
			}
			else
			{
				uniqueNames = new HashSet<>(10);
			}
			uniqueNames.add(name);
			multiMap.put(namePermutationKey, uniqueNames);
		}
	}

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@NotNull
	public Collection<UniqueName> retrieve(@NotNull final NamePermutationKey namePermutationKey)
	{
		@Nullable final Set<UniqueName> uniqueNames = multiMap.get(namePermutationKey);
		return uniqueNames == null ? NoUniqueNames : uniqueNames;
	}
}

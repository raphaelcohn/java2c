package com.guru.webtech;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static java.lang.System.out;

public final class Guru
{
	private Guru()
	{
	}

	public static void main(@NotNull final String... commandLineArguments)
	{
		final NamePermutationMultiMap namePermutationMultiMap = new NamePermutationMultiMap();
		namePermutationMultiMap.addNewUniqueNames("Gurumoorthy Raghupathy", "Raphael Cohn");

		final NamePermutationKey namePermutationKey = NamePermutationKey.namePermutationKey("Gu");
		final Collection<UniqueName> gu = namePermutationMultiMap.retrieve(namePermutationKey);
		out.println("gu = " + gu);
	}

}

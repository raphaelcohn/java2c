package com.compilerUser.moduleName;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ModuleName
{
	@NonNls
	@NotNull
	private final String moduleName;

	public ModuleName(@NotNull final String moduleName) throws IllegalModuleNameException
	{
		//noinspection HardcodedFileSeparator
		if (moduleName.contains("\0") || moduleName.contains("/"))
		{
			throw new IllegalModuleNameException(moduleName);
		}
		this.moduleName = moduleName;
	}

	@NotNull
	public String asString()
	{
		return moduleName;
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

		final ModuleName that = (ModuleName) obj;

		return moduleName.equals(that.moduleName);
	}

	@Override
	public int hashCode()
	{
		return moduleName.hashCode();
	}

	@Override
	@NotNull
	public String toString()
	{
		return moduleName;
	}
}

package com.stormmq.java2c.transpiler.javaModules;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ModuleName
{
	@NotNull private final String moduleName;

	public ModuleName(@NotNull final String moduleName) throws IllegalModuleNameException
	{
		if (moduleName.contains("\0") || moduleName.contains("/"))
		{
			throw new IllegalModuleNameException(moduleName);
		}
		this.moduleName = moduleName;
	}

	@Override
	public boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final ModuleName that = (ModuleName) o;

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

	@NotNull
	public String asString()
	{
		return moduleName;
	}
}

package com.java2c.transpiler.conversion.elementConverters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CType
{
	@NotNull private final String cTypeName;
	@Nullable private final String includeWithAngleBracketsOrDoubleQuotes;

	public CType(@NotNull final String cTypeName, @Nullable final String includeWithAngleBracketsOrDoubleQuotes)
	{
		this.cTypeName = cTypeName;
		this.includeWithAngleBracketsOrDoubleQuotes = includeWithAngleBracketsOrDoubleQuotes;
	}
}

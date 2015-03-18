package com.stormmq.java2c.model.primitives;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
public final class pointer<P>
{
	@NotNull private final P pointsTo;

	public pointer(@NotNull final P pointsTo)
	{
		this.pointsTo = pointsTo;
	}
}

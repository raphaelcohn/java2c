package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import org.jetbrains.annotations.NotNull;

public final class pointer<P extends Primitive>
{
	@NotNull private final P pointsTo;

	public pointer(@NotNull final P pointsTo)
	{
		this.pointsTo = pointsTo;
	}
}

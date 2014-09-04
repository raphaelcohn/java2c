package com.stormmq.java2c.samples3;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractSerialisingVisitor implements SerialisingVisitor
{
	protected AbstractSerialisingVisitor()
	{
	}

	@NotNull
	@Override
	public final String convert()
	{
		throw new UnsupportedOperationException();
	}

	public abstract Object echo();
}

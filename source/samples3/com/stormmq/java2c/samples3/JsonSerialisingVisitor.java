package com.stormmq.java2c.samples3;

public final class JsonSerialisingVisitor extends AbstractSerialisingVisitor
{
	public JsonSerialisingVisitor()
	{
	}

	@Override
	public Integer echo()
	{
		return 10;
	}

	@Override
	public void visit()
	{
		throw new UnsupportedOperationException();
	}
}

package com.stormmq.java2c.samples3;

import org.jetbrains.annotations.NotNull;

public interface SerialisingVisitor extends Visitor, Converter
{
	@NotNull
	@Override
	String convert();
}

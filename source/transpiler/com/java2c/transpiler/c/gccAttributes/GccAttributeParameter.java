package com.java2c.transpiler.c.gccAttributes;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

public final class GccAttributeParameter implements Writable
{
	@NotNull private final String value;

	public GccAttributeParameter(@NotNull final String value)
	{
		this.value = '"' + value + '"';
	}

	public GccAttributeParameter(final int value)
	{
		assert value >= 0;
		this.value = Integer.toString(value);
	}

	public void write(@NotNull final Writer writer) throws IOException
	{
		writer.write(value);
	}
}

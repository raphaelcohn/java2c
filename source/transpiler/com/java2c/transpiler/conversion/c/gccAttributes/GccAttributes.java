package com.java2c.transpiler.conversion.c.gccAttributes;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public final class GccAttributes<N extends Enum<N> & AttributeName> implements Writable
{
	@NotNull private final List<GccAttribute<N>> gccAttributes;

	public GccAttributes(@NotNull final List<GccAttribute<N>> gccAttributes)
	{
		this.gccAttributes = gccAttributes;
	}

	@Override
	public void write(@NotNull final Writer writer) throws IOException
	{
		if (gccAttributes.isEmpty())
		{
			return;
		}

		writer.write(" __attribute__ ((");
		boolean afterFirst = false;
		for (GccAttribute<N> gccAttribute : gccAttributes)
		{
			if (afterFirst)
			{
				writer.write(", ");
			}
			else
			{
				afterFirst = true;
			}
			gccAttribute.write(writer);
		}
		writer.write("))");
	}
}

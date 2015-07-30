/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.c.gccAttributes;

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

/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.c.gccAttributes;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

public final class GccAttribute<N extends Enum<N> & AttributeName> implements Writable
{
	@NotNull private final N name;
	@NotNull private final GccAttributeParameter[] gccAttributeParameters;

	@SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
	public GccAttribute(@NotNull final N name, @NotNull GccAttributeParameter... gccAttributeParameters)
	{
		this.name = name;
		this.gccAttributeParameters = gccAttributeParameters;
	}

	@Override
	public void write(@NotNull final Writer writer) throws IOException
	{
		writer.write(name.name());
		if (gccAttributeParameters.length != 0)
		{
			writer.write(" (");
			boolean afterFirst = false;
			for (final GccAttributeParameter gccAttributeParameter : gccAttributeParameters)
			{
				if (afterFirst)
				{
					writer.write(", ");
				}
				else
				{
					afterFirst = true;
				}
				gccAttributeParameter.write(writer);
			}
			writer.write(')');
		}
	}
}

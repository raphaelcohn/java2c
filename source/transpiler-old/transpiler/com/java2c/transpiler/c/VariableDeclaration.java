/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.c;

import com.java2c.transpiler.c.gccAttributes.GccAttributes;
import com.java2c.transpiler.c.gccAttributes.Writable;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

import static com.java2c.transpiler.c.StorageClass.auto;

public final class VariableDeclaration implements Writable
{
	@NotNull private final StorageClass storageClass;
	private final boolean isConst;
	private final boolean isVolatile;
	@NotNull private final String cTypeName;
	@NotNull private final String name;
	@NotNull private final GccAttributes<GccVariableAttributeName> gccAttributes;

	// The cTypeName needs to include any 'pointer' attributes, eg restrict, volatile, const, *
	public VariableDeclaration(@NotNull final StorageClass storageClass, final boolean isConst, final boolean isVolatile, @NotNull final String cTypeName, @NotNull final String name, @NotNull final GccAttributes<GccVariableAttributeName> gccAttributes)
	{
		this.storageClass = storageClass;
		this.isConst = isConst;
		this.isVolatile = isVolatile;
		this.cTypeName = cTypeName;
		this.name = name;
		this.gccAttributes = gccAttributes;
	}

	public void write(@NotNull final Writer writer) throws IOException
	{
		if (storageClass != auto)
		{
			writer.write(storageClass.storageClass);
			writer.write(' ');
		}

		if (isConst)
		{
			writer.write("const ");
		}

		if (isVolatile)
		{
			writer.write("volatile ");
		}

		writer.write(cTypeName);
		writer.write(' ');
		writer.write(name);

		gccAttributes.write(writer);

		writer.write(';');
	}
}

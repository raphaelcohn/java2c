package com.java2c.transpiler.conversion.c;

import com.java2c.transpiler.conversion.c.gccAttributes.GccAttributes;
import com.java2c.transpiler.conversion.c.gccAttributes.Writable;
import com.java2c.transpiler.conversion.c.gccAttributes.variable.GccVariableAttributeName;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

import static com.java2c.transpiler.conversion.c.StorageClass.auto;

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

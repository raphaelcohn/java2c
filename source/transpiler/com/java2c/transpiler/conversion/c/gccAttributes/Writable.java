package com.java2c.transpiler.conversion.c.gccAttributes;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

public interface Writable
{
	void write(@NotNull final Writer writer) throws IOException;
}

package com.stormmq.java2c.samples;

import com.apple.jobjc.Pointer;
import com.stormmq.java2c.model.Struct;
import com.stormmq.java2c.model.functions.*;
import com.stormmq.java2c.model.primitives.char_;
import com.stormmq.java2c.model.primitives.signed_int;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.stormmq.java2c.model.functions.FormatArgArchetype.printf;
import static com.stormmq.java2c.model.functions.Inlining.Force;
import static com.stormmq.java2c.model.functions.Purity.Constant;
import static com.stormmq.java2c.model.primitives.signed_int.signed_int;

// Make use of org.eclipse.jdt.core, eg import org.eclipse.jdt.core.dom.CompilationUnit, from http://www.eclipse.org/jdt/core/index.php
// http://www.vogella.com/articles/EclipseJDT/article.html
public class Single extends Struct
{
	@SuppressWarnings("UnusedDeclaration") @Deprecated public static final Integer a = 50;
	private static int aa = 100;
	public static int b;
	static
	{
		b = 20;
	}
	@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) private static int c = 10;
	static
	{
		c = 20;
	}

	private final int index;

	@Override
	public boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final Single single = (Single) o;

		return index == single.index;
	}

	@Override
	@inline(Force)
	@flatten
	@hot
	public int hashCode()
	{
		return index;
	}

	@pure(Constant)
	@cold
	public static int divideByTwo(final int value)
	{
		return value / 2;
	}

	@Deprecated
	private void printHello()
	{
	}

	@SuppressWarnings("UnusedDeclaration")
	private static void notUsed(@NotNull @sentinel final Object... varargs)
	{
	}

	// In this case, because c_signed_int is a primitive, we do not generate an __attribute__((returns_nonnull))
	// Nor do we generate __attribute__((nonnull(varargs))) for varargs
	@NotNull
	private static signed_int myPrintf(@NotNull final Pointer<signed_int> something, @NotNull @format_arg final Pointer<char_> my_format, @NotNull @format(printf) Object... varargs)
	{
		return signed_int((short) 10);
	}

	public Single(final int index)
	{
		//super(allocator);
		this.index = index;
	}
}

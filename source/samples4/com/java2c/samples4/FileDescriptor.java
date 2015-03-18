package com.java2c.samples4;

import com.java2c.samples4.builtin.c_signed_int;
import com.java2c.samples4.scalars.Equality;
import com.java2c.samples4.stddef.size_t;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static com.java2c.samples4.stdint.stdint.stdint;

/*
	All calls are transformed
	eg ssize_t read(VoidPointer buf, size_t len) => ssize_t read(int value, void * buf, size_t len)

	All standard object methods (equals, hashCode, toString) are automatically implemented

	clone, notify, wait(), etc are NOT supported

	Actually, all fake objects just map back to a primitive c type (int, char, unsigned int, etc)
	The alternative is attributes against every int, etc - not scalable


	A FileDescriptor class is really a convenience for a FD
		- FDs are just c ints (in our parlance, c_signed_int)
		- Creating a class that wraps a single member is plain wrong


 */
@Scalar("int")
public final class FileDescriptor extends SynthenticScalarObject implements Equality<FileDescriptor>
{
	// TODO: Validate that stdin is actually the macro to use!
	@CCodeTemplate(value = "stdin", includes = stdint)
	public static final FileDescriptor stdin = new FileDescriptor(new c_signed_int(0L));

	@CCodeTemplate(value = "stdout", includes = stdint)
	public static final FileDescriptor stdout = new FileDescriptor(new c_signed_int(1L));

	@CCodeTemplate(value = "stderr", includes = stdint)
	public static final FileDescriptor stderr = new FileDescriptor(new c_signed_int(2L));

	@NotNull
	private final c_signed_int value;

	@CCodeTemplate(value = "(open(name, flags, mode))", includes = xxx)
	public FileDescriptor(@NotNull final Path name, @NotNull final c_number flags, @NotNull final mode_t mode)
	{
		// calls open, does some work!
		this(open(name, flags, mode));
	}

	public FileDescriptor(@NotNull final c_signed_int value)
	{
		this.value = value;
	}

	@Override
	@SuppressWarnings({"OverloadedMethodsWithSameNumberOfParameters", "CovariantEquals"})
	public boolean equals(@NotNull final FileDescriptor right)
	{
		return value.equals(right.value);
	}

	@Override
	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	public boolean notEquals(@NotNull final FileDescriptor right)
	{
		return value.notEquals(right.value);
	}

	ssize_t read(@NotNull final VoidPointer buf, @NotNull final size_t len);
	
	@CCodeTemplate(value = "close(@this@)", includes = xxx)
	public void close()
	{
		xxx;
	}
}

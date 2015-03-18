package com.stormmq.java2c.samples.stdlib;

import com.stormmq.java2c.model.C;
import com.stormmq.java2c.model.primitives.char_;
import com.stormmq.java2c.model.primitives.pointer;
import org.jetbrains.annotations.NotNull;

/*
	Should have no instance methods
	Should have a private constructor
	Should have only public static methods, ideally we'd like to make these native.

	It would be nice to be able to marshal using primitives
		- ultimately, we want to be able to write dynamic glue code to support this API, so we can test from Java!
		- very similar to the BridJ code, but written by us

	Pointers are interesting
		- Structs are passed as references; we'll need special syntax to pass-by-value
			- the only times passing a struct by value makes sense are:-
				- when it's less than 8-bytes (4-bytes on 32-bit systems) [some might argue that 16 bytes makes sense]
				- when we want an explicit copy (not likely)
				- when a library requires it
 */
public final class Stdlib extends C
{
	private Stdlib()
	{
	}

	// var-args handling is going to be a barrel of laughs!
	/*
		This translates as:-

		The annoying thing is that restrict could be used, but we have to know the object we're passing is immutable...

		void printf(const char *format, ...)
		{
			...
		}
	 */
	// zero-terminated - worth pointing out?
	public static native void printf(@NotNull final pointer<char_> format, @NotNull final Object... varargs);
}

package com.java2c.samples;

import com.java2c.libraries.builtin.signed_char;
import com.java2c.model.pointer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"UnusedDeclaration", "ClassWithTooManyFields"})
public interface AnInterface
{
	/*
		It is a compile error to have a Nullable (or null assigned)

		com_java2c_samples_AnInterface.h
			extern const signed char com_java2c_samples_AnInterface_SignedChar;

		com_java2c_samples_AnInterface.c
			const signed char com_java2c_samples_AnInterface_SignedChar = (100);
	 */
	@NotNull
	signed_char SignedChar = new signed_char(100L);

	/*
		const is really evil: http://duramecho.com/ComputerInformation/WhyHowCppConst.html

		com_java2c_samples_AnInterface.h
			extern const void * const com_java2c_samples_AnInterface_VoidPointer = NULL;

		com_java2c_samples_AnInterface.c
			const void * const com_java2c_samples_AnInterface_VoidPointer = NULL;
	 */
	@Nullable
	pointer<?> VoidPointer = null;

	@Nullable
	pointer<signed_char> SignedCharPointer = null;

	@Nullable
	pointer<pointer<?>> VoidVoidPointer = null;


	/*
		boolean (_Bool) and long double are rather painful; the former because it would be nice to use a java primitive,
		the latter because there is nothing in Java to support it.

		Same goes for int128, etc.
	 */
}

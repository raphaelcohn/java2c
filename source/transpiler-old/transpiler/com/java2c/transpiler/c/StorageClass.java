/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.c;

import org.jetbrains.annotations.NotNull;

public enum StorageClass
{
	auto("auto"),
	extern("extern"),
	_static("static"),
	register("register"),
	extern_thread_local("extern __thread"),
	static_thread_local("static __thread"),
	;

	@NotNull public final String storageClass;

	StorageClass(@NotNull final String storageClass)
	{
		this.storageClass = storageClass;
	}
}

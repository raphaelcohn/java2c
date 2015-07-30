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

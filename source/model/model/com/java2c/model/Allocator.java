/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.model;

public enum Allocator
{
	Stack,
	ManualHeap,
	ReferenceCountedHeap,
	GarbageCollectedHeap,

    /*
        ThreadLocalHeap / NumaHeap
        UContext Stack
        UContext Heap
        Connection Heap / Specialist Heap

        etc
     */

	// TODO: Memory alignment as well
}

/*

	For Stack, the following would happen:-

		For a struct:-

			final struct MyStruct instance = { <constructor arguments less the first one, > };
			// To solve: can {} be omitted if there are no arguments
			// To solve: exceptions thrown by struct constructors
			// To solve: struct constructors that do more than set values

		For a class:-

			final struct MyClass instance = <constructor logic, using variable length arrays [not alloca]>
			// To solve: variable length arrays do not work


 */

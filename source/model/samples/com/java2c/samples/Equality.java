/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.samples;

import com.java2c.model.attributes.functions.pure;
import org.jetbrains.annotations.Nullable;

public interface Equality
{
	int Hello = 7;

	int argument = Hello;

	// Uses Object, which is DeprecatedStaticField bit of DeprecatedStaticField problem
	// Pure because isEqual should be side-effect free
	// Not constant as containers (eg hash maps) can have objects added or removed
	// However, could be treated as Constant in some sub-classes because the memory it uses is immutable
	@pure
	boolean equals(@Nullable final Object o);

	// Pure because hashCode should be side-effect free
	// Should not be Constant because hashCode accesses global memory (the object fields)
	// However, could be treated as Constant in some sub-classes because the memory it uses is immutable
	@pure
	int hashCode();
}

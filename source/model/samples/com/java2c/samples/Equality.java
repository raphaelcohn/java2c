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

package com.stormmq.java2c.samples;

import com.stormmq.java2c.model.functions.pure;
import org.jetbrains.annotations.Nullable;

public interface Equality
{
	int Hello = 7;

	int argument = Hello;

	// Uses Object, which is a bit of a problem
	// Pure because equals should be side-effect free
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

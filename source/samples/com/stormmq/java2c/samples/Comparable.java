package com.stormmq.java2c.samples;

import com.stormmq.java2c.model.CObject;
import com.stormmq.java2c.model.functions.pure;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.*;

public interface Comparable
{
	void horrid(AtomicInteger atomicInteger, AtomicBoolean atomicBoolean, AtomicLong a, AtomicLongArray aa, AtomicIntegerArray z, AtomicReference<?> ref);

	@pure
	int compareTo(@Nullable final CObject that);
}

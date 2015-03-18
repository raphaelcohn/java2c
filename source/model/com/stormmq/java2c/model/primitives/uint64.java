package com.stormmq.java2c.model.primitives;

import com.stormmq.java2c.model.Primitive;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassNamingConvention")
@PrimitiveConversion(value = "uint64_t", systemHeader = "stdint.h", isUnsigned = true)
public class uint64 extends Primitive
{
	protected final long value;

	public uint64(final long value)
	{
		this.value = value;
	}

	@SuppressWarnings({"MethodNameSameAsClassName", "OverloadedMethodsWithSameNumberOfParameters"})
	@NotNull
	public static uint64 uint64(final long value)
	{
		return new uint64(value);
	}

//	// http://www.javamex.com/java_equivalents/unsigned_arithmetic.shtml
//	public static boolean unsignedLongIsLessThan(@uint64 final long left, @uint64 final long right)
//	{
//		return left < right ^ left < 0L != right < 0L;
//	}
//
//	// Inspired by Java 8 http://hg.openjdk.java.net/jdk8/tl/jdk/rev/71200c517524 and https://blogs.oracle.com/darcy/entry/unsigned_api
//	@SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
//	public static int compareUnsigned(@uint64 final long left, @uint64 final long right)
//	{
//		return Long.compare(left + Long.MIN_VALUE, right + Long.MIN_VALUE);
//	}
//
//	// Inspired by Java 8
//	public static long divideUnsigned(@uint64 final long dividend, @uint64 final long divisor)
//	{
//		if (divisor < 0L)
//		{
//			return compareUnsigned(dividend, divisor) < 0 ? 0L : 1L;
//		}
//
//		if (dividend > 0L)
//		{
//			return dividend / divisor;
//		}
//		else
//		{
//            /*
//             * For simple code, leveraging BigInteger.  Longer and faster
//             * code written directly in terms of operations on longs is
//             * possible; see "Hacker's Delight" for divide and remainder
//             * algorithms.
//             */
//			return toUnsignedBigInteger(dividend).divide(toUnsignedBigInteger(divisor)).longValue();
//		}
//	}
//
//	@uint64
//	public static long remainderUnsigned(@uint64 final long dividend, @uint64 final long divisor)
//	{
//		if (dividend > 0L && divisor > 0L)
//		{
//			return dividend % divisor;
//		}
//		else
//		{
//			if (compareUnsigned(dividend, divisor) < 0)
//			{
//				return dividend;
//			}
//			else
//			{
//				return toUnsignedBigInteger(dividend).remainder(toUnsignedBigInteger(divisor)).longValue();
//			}
//		}
//	}
//
//	@NotNull
//	private static BigInteger toUnsignedBigInteger(@uint64 final long value)
//	{
//		if (value >= 0L)
//		{
//			return valueOf(value);
//		}
//		else
//		{
//			@uint32 final int upper = (int) (value >>> 32);
//			@uint32 final int lower = (int) value;
//			return valueOf(signedIntegerToUnsignedInteger(upper)).shiftLeft(32).add(valueOf(signedIntegerToUnsignedInteger(lower)));
//		}
//	}
}

package com.java2c.model.scalars;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.other.CType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

@SuppressWarnings("ClassWithTooManyMethods")
public abstract class AbstractScalar<T extends AbstractScalar<T>> implements CType
{
	protected final long value;

	protected AbstractScalar(final long value)
	{
		this.value = value;
	}

	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	@Override
	public final boolean equals(@Nullable final Object obj)
	{
		throw new UnsupportedOperationException("equal is not supported for AbstractScalar");
	}

	@Override
	public final int hashCode()
	{
		throw new UnsupportedOperationException("hashCode is not supported for AbstractScalar");
	}

	@Override
	public final String toString()
	{
		// TODO: Actually, take into account platform bitness; use string formatting in C
		return Long.toString(value);
	}

	@SuppressWarnings("NoopMethodInAbstractClass")
	@Override
	protected final void finalize()
	{
	}

	@SuppressWarnings("CloneInNonCloneableClass")
	@Override
	protected final Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	// TODO: Implementing the CCodeTemplate requires accessing to's type T then the @Scalar on T and enforcing that 'to' is a literal, eg int8_t.class
	@CCodeTemplate("((@to:class@) @this@)")
	@NotNull
	public final <V extends AbstractScalar<V>> V cast(@NotNull final Class<V> to)
	{
		if (!AbstractScalar.class.isAssignableFrom(to))
		{
			throw new IllegalArgumentException("to must be a subclass of AbstractScalar");
		}
		if (to.equals(AbstractScalar.class))
		{
			throw new IllegalArgumentException("to can not be AbstractScalar");
		}
		final Constructor<V> constructor;
		try
		{
			constructor = to.getConstructor(long.class);
		}
		catch (final NoSuchMethodException e)
		{
			throw new IllegalStateException(format(ENGLISH, "It is a class definition error to not have a public constructor that takes a value for a scalar type '%1$s'", to.getSimpleName()), e); //NON-NLS
		}
		try
		{
			return constructor.newInstance(value);
		}
		catch (final InstantiationException e)
		{
			throw new IllegalStateException(format(ENGLISH, "It is a class definition error to not be able to instantiate using a constructor that takes a value for a scalar type '%1$s'", to.getSimpleName()), e); //NON-NLS
		}
		catch (final IllegalAccessException e)
		{
			throw new IllegalStateException(format(ENGLISH, "It is a class definition error to not be able to access a constructor that takes a value for a scalar type '%1$s'", to.getSimpleName()), e); //NON-NLS
		}
		catch (final InvocationTargetException e)
		{
			throw new IllegalStateException(format(ENGLISH, "It is a class definition error to not be able to invoke a constructor that takes a value for a scalar type '%1$s'", to.getSimpleName()), e); //NON-NLS
		}
	}

	@SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
	protected final boolean equals(@NotNull final AbstractScalar<T> right)
	{
		return value == right.value;
	}

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	protected final boolean notEquals(@NotNull final AbstractScalar<T> right)
	{
		return value != right.value;
	}

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	protected final boolean greaterThan(@NotNull final AbstractScalar<T> right)
	{
		return value > right.value;
	}

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	protected final boolean lessThan(@NotNull final AbstractScalar<T> right)
	{
		return value < right.value;
	}

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	protected final boolean greaterThanOrEqualTo(@NotNull final AbstractScalar<T> right)
	{
		return value >= right.value;
	}

	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	protected final boolean lessThanOrEqualTo(@NotNull final AbstractScalar<T> right)
	{
		return value <= right.value;
	}

	@NotNull
	protected final T add(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value + right.value);
	}

	@NotNull
	protected final T subtract(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value - right.value);
	}

	@NotNull
	protected final T multiply(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value * right.value);
	}

	@NotNull
	protected final T divide(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value / right.value);
	}

	@NotNull
	protected final T modulus(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value % right.value);
	}

	@NotNull
	protected final T and(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value & right.value);
	}

	@NotNull
	protected final T or(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value | right.value);
	}

	@NotNull
	protected abstract T constructT(final long value);
}

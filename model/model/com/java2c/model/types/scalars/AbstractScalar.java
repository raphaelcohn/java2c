package com.java2c.model.types.scalars;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.other.literal;
import com.java2c.model.types.AbstractCType;
import com.java2c.model.types.Banished;
import com.java2c.model.types.Uncastable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

@Uncastable
public abstract class AbstractScalar<T extends AbstractScalar<T>> extends AbstractCType
{
	private final long value;

	@Banished
	protected AbstractScalar(@literal final long value)
	{
		this.value = value;
	}

	// TODO: Implementing the CCodeTemplate requires accessing to's type V then the @Scalar on V and enforcing that 'to' is a literal, eg int8_t.class
	@CCodeTemplate("((@to:class@) @this@)")
	@NotNull
	public final <V extends AbstractScalar<V>> V cast(@literal @NotNull final Class<V> to)
	{
		if (!AbstractScalar.class.isAssignableFrom(to))
		{
			throw new IllegalArgumentException("'to' must be a subclass of AbstractScalar");
		}
		if (to.equals(AbstractScalar.class))
		{
			throw new IllegalArgumentException("'to' can not be AbstractScalar");
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

	@Banished
	public long value()
	{
		return value;
	}

	@Banished
	@NotNull
	protected abstract T constructT(final long value);

	@Banished
	protected final boolean isEqual(@NotNull final AbstractScalar<T> right)
	{
		return value == right.value;
	}

	@Banished
	protected final boolean isNotEqual(@NotNull final AbstractScalar<T> right)
	{
		return value != right.value;
	}

	@Banished
	protected final boolean isGreaterThan(@NotNull final AbstractScalar<T> right)
	{
		return value > right.value;
	}

	@Banished
	protected final boolean isLessThan(@NotNull final AbstractScalar<T> right)
	{
		return value < right.value;
	}

	@Banished
	protected final boolean isGreaterThanOrEqualTo(@NotNull final AbstractScalar<T> right)
	{
		return value >= right.value;
	}

	@Banished
	protected final boolean isLessThanOrEqualTo(@NotNull final AbstractScalar<T> right)
	{
		return value <= right.value;
	}

	@Banished
	@NotNull
	protected final T add(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value + right.value);
	}

	@Banished
	@NotNull
	protected final T subtract(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value - right.value);
	}

	@Banished
	@NotNull
	protected final T multiply(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value * right.value);
	}

	@Banished
	@NotNull
	protected final T divide(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value / right.value);
	}

	@Banished
	@NotNull
	protected final T modulus(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value % right.value);
	}

	@Banished
	@NotNull
	protected final T and(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value & right.value);
	}

	@Banished
	@NotNull
	protected final T or(@NotNull final AbstractScalar<T> right)
	{
		return constructT(value | right.value);
	}

	@Banished
	@NotNull
	protected final T negateT()
	{
		return constructT(-value);
	}
}

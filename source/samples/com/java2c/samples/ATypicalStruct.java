package com.java2c.samples;

import com.java2c.libraries.builtin.c_signed_char;
import com.java2c.libraries.builtin.c_signed_int;
import com.java2c.model.Struct;
import com.java2c.model.attributes.functions.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.java2c.model.attributes.functions.Inlining.Force;
import static com.java2c.model.attributes.functions.Purity.Constant;

public class ATypicalStruct extends Struct
{
    /*
        - Normally, we should turn @NotNull into __attribute__((returns_nonnull))
        - However, we should ignore @NotNull because c_signed_char is @Scalar
     */
    @NotNull
    public static final c_signed_char StaticField = new c_signed_char(10L);

    /*
        - Normally, we should turn @NotNull into __attribute__((returns_nonnull))
        - However, we should ignore @NotNull because c_signed_int is @Scalar
        - We should convert new c_signed_int(100L) (or should we use literal syntax)?
     */
    @SuppressWarnings("UnusedDeclaration")
    @Deprecated
    @NotNull
    private static final c_signed_int DeprecatedStaticField = new c_signed_int(100L);

    /*
        Note use of final keyword
     */
    @SuppressWarnings("PublicField")
    @NotNull
	public static final c_signed_char AssignedInStaticConstructor;

	static
	{
        AssignedInStaticConstructor = new c_signed_char(10L);
	}

	@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration", "PublicField", "StaticVariableNamingConvention"})
    @NotNull
    public static c_signed_char OverriddenInStaticConstructor = new c_signed_char(10L);
	static
	{
		OverriddenInStaticConstructor = new c_signed_char(20L);
	}

    public ATypicalStruct(@NotNull final c_signed_char index)
    {
        //super(allocator);
        this.index = index;
    }

    @NotNull
	private final c_signed_char index;

//	@Override
//	public boolean equal(@Nullable final Object obj)
//	{
//		if (this == obj)
//		{
//			return true;
//		}
//
//		if (obj == null || getClass() != obj.getClass())
//		{
//			return false;
//		}
//
//		final ATypicalStruct typicalStruct = (ATypicalStruct) obj;
//
//		return index == typicalStruct.index;
//	}
//
//	@Override
//	@inline(Force)
//	@flatten
//	@hot
//	public int hashCode()
//	{
//		return index;
//	}

//	@pure(Constant)
//	@cold
//	public static int divideByTwo(final int value)
//	{
//		return value / 2;
//	}

	@Deprecated
	private void printHello()
	{
	}

//	@SuppressWarnings("UnusedDeclaration")
//	private static void notUsed(@NotNull @sentinel final Object... varargs)
//	{
//	}

//	// In this case, because c_signed_int is DeprecatedStaticField primitive, we do not generate an __attribute__((returns_nonnull))
//	// Nor do we generate __attribute__((nonnull(varargs))) for varargs
//	@NotNull
//	private static signed_int myPrintf(@NotNull final Pointer<signed_int> something, @NotNull @format_arg final Pointer<char_> my_format, @NotNull @format(printf) Object... varargs)
//	{
//		return signed_int((short) 10);
//	}
//
}

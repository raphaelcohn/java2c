package com.java2c.libraries.c.stddef;

import com.java2c.model.other.CCodeTemplate;
import com.java2c.model.other.NotAnObject;
import com.java2c.model.other.PreprocessorConstant;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"ClassNamePrefixedWithPackageName", "ClassNamingConvention", "UtilityClass"})
@NotAnObject
public final class stddef
{
	@NonNls @SuppressWarnings("ConstantNamingConvention") @NotNull public static final String stddef = "<stddef.h>";

	@Nullable @PreprocessorConstant @CCodeTemplate(value = "NULL", includes = stddef) public static final Object NULL = null;

//	@CCodeTemplate(value = "offsetof(@type:class@, @field:unquoted@)", includes = stddef)
//	@NotNull
//	public static size_t offsetof(@NotNull final Class<? extends Structure> type, @NotNull final String field)
//	{
//		// TODO: An useful real implementation
//		return new size_t(0L);
//	}

	private stddef()
	{
	}
}

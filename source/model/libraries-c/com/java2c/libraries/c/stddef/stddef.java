/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

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

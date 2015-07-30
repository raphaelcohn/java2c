/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.QualifiedNameable;
import java.util.HashSet;
import java.util.Set;

public class UnannotatedBanishedMethods
{
	@NotNull
	private final Set<String> banished;

	public UnannotatedBanishedMethods()
	{
		banished = new HashSet<>(4);
	}

	public void banish(@NonNls @NotNull final String className, @NonNls @NotNull final String... methodNames)
	{
		for (final String methodName : methodNames)
		{
			banished.add(concatenate(className, methodName));
		}
	}

	public boolean isBanished(@SuppressWarnings("TypeMayBeWeakened") @NotNull final ExecutableElement executableElement)
	{
		final QualifiedNameable enclosingClass = (QualifiedNameable) executableElement.getEnclosingElement();
		assert enclosingClass != null;
		return isBanished(enclosingClass.getQualifiedName().toString(), executableElement.getSimpleName().toString());
	}

	public boolean isBanished(@NonNls @NotNull final String className, @NonNls @NotNull final String methodName)
	{
		return banished.contains(concatenate(className, methodName));
	}

	@NotNull
	private static String concatenate(@NotNull final String className, @NotNull final String methodName)
	{
		return className + '.' + methodName;
	}
}

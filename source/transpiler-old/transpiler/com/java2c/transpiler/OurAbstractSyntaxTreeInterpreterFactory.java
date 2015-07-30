/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler;

import com.compilerUser.elementHandlers.AbstractSyntaxTreeInterpreterFactory;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public final class OurAbstractSyntaxTreeInterpreterFactory implements AbstractSyntaxTreeInterpreterFactory<OurAbstractSyntaxTreeInterpreter>
{
	@NotNull
	@Override
	public OurAbstractSyntaxTreeInterpreter create(@NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees)
	{
		return new OurAbstractSyntaxTreeInterpreter(typeUtilities, elementUtilities, trees);
	}
}

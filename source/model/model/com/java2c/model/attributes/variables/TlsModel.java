/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.model.attributes.variables;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("EnumeratedConstantNamingConvention")
public enum TlsModel
{
	Default,
	global_dynamic,
	local_dynamic,
	initial_exec,
	local_exec,
	;

	@NotNull public final String tlsModelSpecifier;

	TlsModel()
	{
		tlsModelSpecifier = name().replace('_', '-');
	}
}

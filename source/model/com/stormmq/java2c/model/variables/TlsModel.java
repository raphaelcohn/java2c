package com.stormmq.java2c.model.variables;

import org.jetbrains.annotations.NotNull;

public enum TlsModel
{
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

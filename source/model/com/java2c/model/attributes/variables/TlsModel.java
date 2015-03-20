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

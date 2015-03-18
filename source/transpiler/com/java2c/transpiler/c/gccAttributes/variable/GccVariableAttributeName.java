package com.java2c.transpiler.c.gccAttributes.variable;

import com.java2c.transpiler.c.gccAttributes.AttributeName;

public enum GccVariableAttributeName implements AttributeName
{
	aligned,
	cleanup,
	common,
	nocommon,
	deprecated,
	mode,
	packed,
	section,
	tls_model,
	unused,
	used,
	vector_size,
	weak,
	;
}

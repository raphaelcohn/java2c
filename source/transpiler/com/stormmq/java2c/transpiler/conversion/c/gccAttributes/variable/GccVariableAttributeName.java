package com.stormmq.java2c.transpiler.conversion.c.gccAttributes.variable;

import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.AttributeName;

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

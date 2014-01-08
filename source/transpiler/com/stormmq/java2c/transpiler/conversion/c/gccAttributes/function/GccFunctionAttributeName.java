package com.stormmq.java2c.transpiler.conversion.c.gccAttributes.function;

import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.AttributeName;

public enum GccFunctionAttributeName implements AttributeName
{
	cold,
	flatten,
	format,
	format_arg,
	hot,
	always_inline,
	malloc,
	pure,
	sentinel,
	;
}

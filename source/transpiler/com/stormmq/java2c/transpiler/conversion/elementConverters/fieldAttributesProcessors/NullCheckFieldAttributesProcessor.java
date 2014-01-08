package com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors;

import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.GccAttribute;
import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.variable.GccVariableAttributeName;
import com.stormmq.java2c.transpiler.conversion.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

public final class NullCheckFieldAttributesProcessor extends AbstractFieldAttributesProcessor
{
	@NotNull public static final FieldAttributesProcessor NullCheck = new NullCheckFieldAttributesProcessor();

	private NullCheckFieldAttributesProcessor()
	{
	}

	@Override
	public void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException
	{
		final boolean isNullable = hasAnnotation(field, Nullable.class);
		final boolean isNotNull = hasAnnotation(field, NotNull.class);
		if (isNullable && isNotNull)
		{
			throw newConversionException(field, "may not be marked as @Nullable and @NotNull");
		}
	}
}

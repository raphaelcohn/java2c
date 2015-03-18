package com.java2c.transpiler.elementConverters.fieldAttributesProcessors;

import com.java2c.transpiler.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

import static com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName.deprecated;

public final class DeprecatedFieldAttributesProcessor extends AbstractFieldAttributesProcessor
{
	@NotNull private static final GccAttribute<GccVariableAttributeName> DeprecatedAttribute = new GccAttribute<>(deprecated);
	@NotNull public static final FieldAttributesProcessor Deprecated = new DeprecatedFieldAttributesProcessor();

	private DeprecatedFieldAttributesProcessor()
	{
	}

	@Override
	public void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException
	{
		if (hasAnnotation(field, Deprecated.class))
		{
			gccAttributes.add(DeprecatedAttribute);
		}
	}
}

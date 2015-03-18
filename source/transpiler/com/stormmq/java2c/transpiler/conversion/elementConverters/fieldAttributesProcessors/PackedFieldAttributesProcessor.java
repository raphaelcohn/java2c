package com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors;

import com.java2c.model.attributes.variables.packed;
import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.GccAttribute;
import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.variable.GccVariableAttributeName;
import com.stormmq.java2c.transpiler.conversion.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

import static com.stormmq.java2c.transpiler.conversion.c.gccAttributes.variable.GccVariableAttributeName.packed;

public final class PackedFieldAttributesProcessor extends AbstractFieldAttributesProcessor
{
	@NotNull private static final GccAttribute<GccVariableAttributeName> PackedAttribute = new GccAttribute<>(packed);
	@NotNull public static final FieldAttributesProcessor Packed = new PackedFieldAttributesProcessor();

	private PackedFieldAttributesProcessor()
	{
	}

	@Override
	public void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException
	{
		if (hasAnnotation(field, packed.class))
		{
			gccAttributes.add(PackedAttribute);
		}
	}
}

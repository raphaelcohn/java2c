package com.java2c.transpiler.fieldAttributesProcessors;

import com.java2c.transpiler.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

import static com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName.unused;
import static java.util.Arrays.asList;

public final class UnusedFieldAttributesProcessor extends AbstractFieldAttributesProcessor
{
	@NotNull private static final GccAttribute<GccVariableAttributeName> UnusedAttribute = new GccAttribute<>(unused);
	@NotNull public static final FieldAttributesProcessor Unused = new UnusedFieldAttributesProcessor();

	private UnusedFieldAttributesProcessor()
	{
	}

	@Override
	public void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException
	{
		@Nullable final SuppressWarnings suppressWarnings = field.getAnnotation(SuppressWarnings.class);
		if (suppressWarnings == null)
		{
			return;
		}

		@Nullable final String[] value = suppressWarnings.value();
		if (value != null)
		{
			if (asList(value).contains("UnusedDeclaration"))
			{
				gccAttributes.add(UnusedAttribute);
			}
		}
	}
}

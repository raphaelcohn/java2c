package com.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors;

import com.java2c.model.attributes.variables.aligned;
import com.java2c.transpiler.conversion.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.conversion.c.gccAttributes.GccAttributeParameter;
import com.java2c.transpiler.conversion.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.conversion.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

import static com.java2c.model.attributes.variables.aligned.BiggestAlignment;

public final class AlignmentFieldAttributesProcessor extends AbstractFieldAttributesProcessor
{
	@NotNull private static final GccAttributeParameter[] BiggestAlignmentParameters = new GccAttributeParameter[]{new GccAttributeParameter("__BIGGEST_ALIGNMENT__")};
	@NotNull private static final GccAttributeParameter[] NoAttributeParameters = new GccAttributeParameter[0];

	@NotNull public static final FieldAttributesProcessor Alignment = new AlignmentFieldAttributesProcessor();

	private AlignmentFieldAttributesProcessor()
	{
	}

	@Override
	public void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException
	{
		@Nullable final aligned alignment = field.getAnnotation(aligned.class);
		if (alignment == null)
		{
			return;
		}

		final int value = alignment.value();
		final GccAttributeParameter[] attributeParameters;
		if (value < 0)
		{
			if (value != BiggestAlignment)
			{
				throw newConversionException(field, "may not be marked as aligned with a non-zero alignment other than BiggestAlignment");
			}
			attributeParameters = BiggestAlignmentParameters;
		}
		else if (value == 0)
		{
			attributeParameters = NoAttributeParameters;
		}
		else
		{
			attributeParameters = new GccAttributeParameter[]{ new GccAttributeParameter(value) };
		}
		gccAttributes.add(new GccAttribute<>(GccVariableAttributeName.aligned, attributeParameters));
	}
}

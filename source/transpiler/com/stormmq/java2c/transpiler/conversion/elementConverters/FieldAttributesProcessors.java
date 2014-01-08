package com.stormmq.java2c.transpiler.conversion.elementConverters;

import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.GccAttribute;
import com.stormmq.java2c.transpiler.conversion.c.gccAttributes.variable.GccVariableAttributeName;
import com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.FieldAttributesProcessor;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.AlignmentFieldAttributesProcessor.Alignment;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.DeprecatedFieldAttributesProcessor.Deprecated;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.NullCheckFieldAttributesProcessor.NullCheck;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.PackedFieldAttributesProcessor.Packed;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.SectionFieldAttributesProcessor.Section;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.ThreadLocalModelFieldAttributesProcessor.ThreadLocalModel;
import static com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors.UnusedFieldAttributesProcessor.Unused;
import static java.util.Arrays.asList;

public final class FieldAttributesProcessors
{
	@NotNull public static final FieldAttributesProcessors StaticFieldAttributesProcessors = new FieldAttributesProcessors
	(
		NullCheck,
		Deprecated,
		Unused,
		Alignment,
		ThreadLocalModel,
		Section,
		Packed
	);

	@NotNull public static final FieldAttributesProcessors InstanceFieldAttributesProcessors = StaticFieldAttributesProcessors;

	@NotNull private final Set<FieldAttributesProcessor> fieldAttributesProcessors;

	public FieldAttributesProcessors(@NotNull final FieldAttributesProcessor... fieldAttributesProcessors)
	{
		this.fieldAttributesProcessors = new LinkedHashSet<>(asList(fieldAttributesProcessors));
	}

	@NotNull
	public List<GccAttribute<GccVariableAttributeName>> processFieldAttributes(@NotNull final VariableElement field) throws ConversionException
	{
		final List<GccAttribute<GccVariableAttributeName>> gccAttributes = new ArrayList<>(4);
		for (FieldAttributesProcessor fieldAttributesProcessor : fieldAttributesProcessors)
		{
			fieldAttributesProcessor.processField(gccAttributes, field);
		}
		return gccAttributes;
	}
}

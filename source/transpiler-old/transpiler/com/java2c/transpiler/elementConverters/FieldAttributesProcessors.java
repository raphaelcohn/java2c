/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.elementConverters;

import com.java2c.transpiler.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.fieldAttributesProcessors.FieldAttributesProcessor;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.java2c.transpiler.fieldAttributesProcessors.AlignmentFieldAttributesProcessor.Alignment;
import static com.java2c.transpiler.fieldAttributesProcessors.DeprecatedFieldAttributesProcessor.Deprecated;
import static com.java2c.transpiler.fieldAttributesProcessors.NullCheckFieldAttributesProcessor.NullCheck;
import static com.java2c.transpiler.fieldAttributesProcessors.PackedFieldAttributesProcessor.Packed;
import static com.java2c.transpiler.fieldAttributesProcessors.SectionFieldAttributesProcessor.Section;
import static com.java2c.transpiler.fieldAttributesProcessors.ThreadLocalModelFieldAttributesProcessor.ThreadLocalModel;
import static com.java2c.transpiler.fieldAttributesProcessors.UnusedFieldAttributesProcessor.Unused;
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

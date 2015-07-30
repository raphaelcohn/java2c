/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.fieldAttributesProcessors;

import com.java2c.model.attributes.variables.section;
import com.java2c.transpiler.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.c.gccAttributes.GccAttributeParameter;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

public final class SectionFieldAttributesProcessor extends AbstractFieldAttributesProcessor
{
	@NotNull public static final FieldAttributesProcessor Section = new SectionFieldAttributesProcessor();

	private SectionFieldAttributesProcessor()
	{
	}

	@Override
	public void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException
	{
		@Nullable final section section = field.getAnnotation(section.class);
		if (section == null)
		{
			return;
		}

		final String sectionName = section.value();
		gccAttributes.add(new GccAttribute<>(GccVariableAttributeName.section, new GccAttributeParameter(sectionName)));
	}
}

/*
 * This file is part of java2c. It is subject to the licence terms in the COPYRIGHT file found in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT. No part of compilerUser, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the COPYRIGHT file.
 * Copyright © 2014-2015 The developers of java2c. See the COPYRIGHT file in the top-level directory of this distribution and at https://raw.githubusercontent.com/raphaelcohn/java2c/master/COPYRIGHT.
 */

package com.java2c.transpiler.fieldAttributesProcessors;

import com.java2c.model.attributes.variables.ThreadLocalModel;
import com.java2c.model.attributes.variables.TlsModel;
import com.java2c.transpiler.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.c.gccAttributes.GccAttributeParameter;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

import static com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName.tls_model;
import static javax.lang.model.element.Modifier.FINAL;

public final class ThreadLocalModelFieldAttributesProcessor extends AbstractFieldAttributesProcessor
{
	@NotNull public static final FieldAttributesProcessor ThreadLocalModel = new ThreadLocalModelFieldAttributesProcessor();

	private ThreadLocalModelFieldAttributesProcessor()
	{
	}

	@Override
	public void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException
	{
		@Nullable final ThreadLocalModel threadLocalModel = field.getAnnotation(ThreadLocalModel.class);
		if (threadLocalModel == null)
		{
			return;
		}

		if (field.getModifiers().contains(FINAL))
		{
			throw newConversionException(field, "may not be defined @ThreadLocalModel because it is static and final");
		}

		@Nullable final TlsModel tlsModel = threadLocalModel.value();
		if (tlsModel == null)
		{
			return;
		}

		gccAttributes.add(new GccAttribute<>(tls_model, new GccAttributeParameter(tlsModel.tlsModelSpecifier)));
	}
}

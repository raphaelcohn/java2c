package com.java2c.transpiler.elementConverters.fieldAttributesProcessors;

import com.java2c.transpiler.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

public interface FieldAttributesProcessor
{
	void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException;
}

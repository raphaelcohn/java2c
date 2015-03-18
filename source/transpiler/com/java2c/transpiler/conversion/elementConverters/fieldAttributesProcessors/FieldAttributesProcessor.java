package com.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors;

import com.java2c.transpiler.conversion.c.gccAttributes.GccAttribute;
import com.java2c.transpiler.conversion.c.gccAttributes.variable.GccVariableAttributeName;
import com.java2c.transpiler.conversion.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.VariableElement;
import java.util.Collection;

public interface FieldAttributesProcessor
{
	void processField(@NotNull final Collection<GccAttribute<GccVariableAttributeName>> gccAttributes, @NotNull final VariableElement field) throws ConversionException;
}

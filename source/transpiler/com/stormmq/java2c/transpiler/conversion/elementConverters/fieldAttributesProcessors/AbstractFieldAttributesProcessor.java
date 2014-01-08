package com.stormmq.java2c.transpiler.conversion.elementConverters.fieldAttributesProcessors;

import com.stormmq.java2c.transpiler.conversion.elementConverters.ConversionException;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.annotation.Annotation;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public abstract class AbstractFieldAttributesProcessor implements FieldAttributesProcessor
{
	protected AbstractFieldAttributesProcessor()
	{
	}

	protected static <A extends Annotation> boolean hasAnnotation(@NotNull final Element element, @NotNull final Class<A> annotation)
	{
		return element.getAnnotation(annotation) != null;
	}

	@NotNull
	protected final ConversionException newConversionException(@NotNull final VariableElement field, @NotNull final String message)
	{
		return new ConversionException(format(ENGLISH, "A field '%1$s' (in '%2$s') %3$s", field.getSimpleName(), inClass(field), message));
	}

	@NotNull
	private String inClass(@NotNull final VariableElement field)
	{
		return ((TypeElement) field.getEnclosingElement()).getQualifiedName().toString();
	}
}

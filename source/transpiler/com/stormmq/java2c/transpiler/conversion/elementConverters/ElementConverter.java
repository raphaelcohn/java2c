package com.stormmq.java2c.transpiler.conversion.elementConverters;

import com.stormmq.java2c.transpiler.conversion.typeResolution.TypeResolver;
import com.stormmq.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public interface ElementConverter<E extends Element>
{
	@NotNull ElementConverter<Element> UnknownElementConverterInstance = new ElementConverter<Element>()
	{
		@Override
		public void convert(@NotNull final Warnings warnings, @NotNull final TypeResolver typeResolver, @NotNull final Element element) throws ConversionException
		{
			throw new ConversionException(format(ENGLISH, "Do not know how to convert elements like '%1$s'", element));
		}
	};

	@NotNull ElementConverter<PackageElement> PackageElementIgnoredElementConverterInstance = new ElementConverter<PackageElement>()
	{
		@Override
		public void convert(@NotNull final Warnings warnings, @NotNull final TypeResolver typeResolver, @NotNull final PackageElement element)
		{
			warnings.warn(format(ENGLISH, "Ignoring elements of kind %1$s", element.getKind().name()));
		}
	};

	@NotNull ElementConverter<TypeElement> TypeElementIgnoredElementConverterInstance = new ElementConverter<TypeElement>()
	{
		@Override
		public void convert(@NotNull final Warnings warnings, @NotNull final TypeResolver typeResolver, @NotNull final TypeElement element)
		{
			warnings.warn(format(ENGLISH, "Ignoring elements of kind %1$s", element.getKind().name()));
		}
	};

	void convert(@NotNull final Warnings warnings, @NotNull final TypeResolver typeResolver, @NotNull final E element) throws ConversionException;
}

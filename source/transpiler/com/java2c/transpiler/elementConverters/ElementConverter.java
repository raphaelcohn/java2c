package com.java2c.transpiler.elementConverters;

import com.java2c.transpiler.typeResolution.TypeResolver;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import static com.java2c.utility.EnglishFormatter.format;
import static javax.tools.Diagnostic.Kind.*;

public interface ElementConverter<E extends Element>
{
	@NotNull ElementConverter<Element> UnknownElementConverterInstance = new ElementConverter<Element>()
	{
		@Override
		public void convert(@NonNls @NotNull final Messager messager, @NotNull final TypeResolver typeResolver, @NotNull final Element element) throws ConversionException
		{
			throw new ConversionException(format("Do not know how to convert elements like '%1$s'", element));
		}
	};

	@NotNull ElementConverter<PackageElement> PackageElementIgnoredElementConverterInstance = new ElementConverter<PackageElement>()
	{
		@Override
		public void convert(@NonNls @NotNull final Messager messager, @NotNull final TypeResolver typeResolver, @NotNull final PackageElement element)
		{
			messager.printMessage(WARNING, "Ignoring PackageElement element", element);
		}
	};

	@NotNull ElementConverter<TypeElement> TypeElementIgnoredElementConverterInstance = new ElementConverter<TypeElement>()
	{
		@Override
		public void convert(@NonNls @NotNull final Messager messager, @NotNull final TypeResolver typeResolver, @NotNull final TypeElement element)
		{
			messager.printMessage(WARNING, "Ignoring TypeElement element", element);
		}
	};

	void convert(@NonNls @NotNull final Messager messager, @NotNull final TypeResolver typeResolver, @NotNull final E element) throws ConversionException;
}

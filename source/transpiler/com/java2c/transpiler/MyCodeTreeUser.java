package com.java2c.transpiler;

import com.java2c.codeTreeUsers.CodeTreeUser;
import com.java2c.transpiler.elementConverters.ConversionException;
import com.java2c.transpiler.elementConverters.ElementConverter;
import com.java2c.transpiler.typeResolution.TypeHelper;
import com.java2c.transpiler.typeResolution.TypeResolver;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.java2c.transpiler.elementConverters.ElementConverter.UnknownElementConverterInstance;
import static java.lang.System.out;
import static javax.lang.model.element.ElementKind.*;
import static javax.tools.Diagnostic.Kind.ERROR;

public final class MyCodeTreeUser implements CodeTreeUser
{
	@NotNull
	private final Map<ElementKind, ElementConverter<?>> topLevelConverters;

	public MyCodeTreeUser(@NotNull final ElementConverter<PackageElement> packageConverter, @NotNull final ElementConverter<TypeElement> enumConverter, @NotNull final ElementConverter<TypeElement> classConverter, @NotNull final ElementConverter<TypeElement> annotationConverter, @NotNull final ElementConverter<TypeElement> interfaceConverter)
	{
		topLevelConverters = new HashMap<ElementKind, ElementConverter<?>>(5)
		{{
			put(PACKAGE, packageConverter);
			put(ENUM, enumConverter);
			put(CLASS, classConverter);
			put(ANNOTATION_TYPE, annotationConverter);
			put(INTERFACE, interfaceConverter);
		}};
	}

	@Override
	public void process(@NotNull final Messager messager, @NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees, @NotNull final Set<? extends Element> rootElements)
	{
		final TypeResolver typeResolver = new TypeResolver(new TypeHelper(typeUtilities, elementUtilities));

		for (final Element rootElement : rootElements)
		{
			@SuppressWarnings("unchecked") @Nullable final ElementConverter<Element> elementConverter = (ElementConverter<Element>) topLevelConverters.get(rootElement.getKind());
			@NotNull final ElementConverter<Element> actualElementConverter = elementConverter == null ? UnknownElementConverterInstance : elementConverter;
			try
			{
				actualElementConverter.convert(messager, typeResolver, rootElement);
			}
			catch (final ConversionException e)
			{
				messager.printMessage(ERROR, e.getMessage());
				return;
			}

			@Nullable final TreePath treePath = trees.getPath(rootElement);
			// null for PackageElement
			if (treePath != null)
			{
				for (final Tree tree : treePath)
				{
					final Kind treeKind = tree.getKind();
					out.println("treeKind = " + treeKind);
				}
			}
		}
	}
}

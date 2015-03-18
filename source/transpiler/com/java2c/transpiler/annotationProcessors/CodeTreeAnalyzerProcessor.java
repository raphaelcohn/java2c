package com.java2c.transpiler.annotationProcessors;

import com.java2c.transpiler.conversion.elementConverters.ConversionException;
import com.java2c.transpiler.conversion.elementConverters.ElementConverter;
import com.java2c.transpiler.conversion.typeResolution.TypeHelper;
import com.java2c.transpiler.conversion.typeResolution.TypeResolver;
import com.java2c.transpiler.javaModules.FatalCompilationException;
import com.java2c.transpiler.warnings.Warnings;
import com.sun.source.tree.Tree;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.java2c.transpiler.conversion.elementConverters.ElementConverter.UnknownElementConverterInstance;
import static javax.lang.model.element.ElementKind.*;

// https://today.java.net/pub/a/today/2008/04/10/source-code-analysis-using-java-6-compiler-apis.html#accessing-the-abstract-syntax-tree-the-compiler-tree-api
public final class CodeTreeAnalyzerProcessor extends AbstractTreeAnalyzerProcessor
{
	@NotNull private final Warnings warnings;
	@NotNull private final Map<ElementKind, ElementConverter<?>> topLevelConverters;

	@Nullable private Trees trees;

	public CodeTreeAnalyzerProcessor(@NotNull final Warnings warnings, @NotNull final ElementConverter<PackageElement> packageConverter, @NotNull final ElementConverter<TypeElement> enumConverter, @NotNull final ElementConverter<TypeElement> classConverter, @NotNull final ElementConverter<TypeElement> annotationConverter, @NotNull final ElementConverter<TypeElement> interfaceConverter)
	{
		this.warnings = warnings;
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
	public void init(@NotNull final ProcessingEnvironment pe)
	{
		super.init(pe);
		trees = Trees.instance(pe);
	}

	@Override
	public boolean process(@NotNull final Set<? extends TypeElement> annotations, @NotNull final RoundEnvironment roundEnv)
	{
		assert typeUtilities != null;
		assert elementUtilities != null;
		assert trees != null;

		final TypeResolver typeResolver = new TypeResolver(new TypeHelper(typeUtilities, elementUtilities));

		for (final Element rootElement : roundEnv.getRootElements())
		{
			@SuppressWarnings("unchecked") @Nullable final ElementConverter<Element> elementConverter = (ElementConverter<Element>) topLevelConverters.get(rootElement.getKind());
			@NotNull final ElementConverter<Element> actualElementConverter = elementConverter == null ? UnknownElementConverterInstance : elementConverter;
			try
			{
				actualElementConverter.convert(warnings, typeResolver, rootElement);
			}
			catch (ConversionException e)
			{
				warnings.fatal(new FatalCompilationException(e.getMessage(), e));
				return true;
			}

			@Nullable final TreePath treePath = trees.getPath(rootElement);
			// null for PackageElement
			if (treePath != null)
			{
				for (final Tree tree : treePath)
				{
					final Tree.Kind treeKind = tree.getKind();
					System.out.println("treeKind = " + treeKind);
				}
			}
		}
		return true;
	}
}

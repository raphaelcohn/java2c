package com.stormmq.java2c.transpiler.annotationProcessors;

import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.List;
import java.util.Set;

import static java.util.Collections.*;
import static javax.lang.model.SourceVersion.RELEASE_7;

public abstract class AbstractTreeAnalyzerProcessor implements Processor
{
	@NotNull private static final Set<String> AllCode = singleton("*");
	@NotNull private static final Set<String> EmptySupportedOptions = emptySet();
	@NotNull private static final List<? extends Completion> EmptyCompletions = emptyList();

	@Nullable protected Types typeUtilities;
	@Nullable protected Elements elementUtilities;
	@Nullable protected Trees trees;

	protected AbstractTreeAnalyzerProcessor()
	{
		trees = null;
	}

	public final void register(@NotNull final List<Processor> processors)
	{
		processors.add(this);
	}

	@Override
	public final Set<String> getSupportedOptions()
	{
		return EmptySupportedOptions;
	}

	@Override
	public final SourceVersion getSupportedSourceVersion()
	{
		return RELEASE_7;
	}

	@Override
	public final Set<String> getSupportedAnnotationTypes()
	{
		return AllCode;
	}

	@Override
	public void init(@NotNull final ProcessingEnvironment processingEnv)
	{
		typeUtilities = processingEnv.getTypeUtils();
		elementUtilities = processingEnv.getElementUtils();
		trees = Trees.instance(processingEnv);
	}

	@Override
	public final Iterable<? extends Completion> getCompletions(@Nullable final Element element, @Nullable final AnnotationMirror annotation, @Nullable final ExecutableElement member, @Nullable final String userText)
	{
		return EmptyCompletions;
	}
}

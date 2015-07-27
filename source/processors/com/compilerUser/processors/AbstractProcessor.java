package com.compilerUser.processors;

import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.List;
import java.util.Set;

import static com.sun.source.util.Trees.instance;
import static java.util.Collections.*;
import static javax.lang.model.SourceVersion.RELEASE_7;

public abstract class AbstractProcessor implements Processor
{
	@NotNull private static final Set<String> AllCode = singleton("*");
	@NotNull private static final Set<String> EmptySupportedOptions = emptySet();
	@NotNull private static final List<? extends Completion> EmptyCompletions = emptyList();

	@Nullable protected Messager messager;
	@Nullable protected Types typeUtilities;
	@Nullable protected Elements elementUtilities;
	@Nullable protected Trees trees;

	protected AbstractProcessor()
	{
		messager = null;
		typeUtilities = null;
		elementUtilities = null;
		trees = null;
	}

	@NotNull
	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@Override
	public final Set<String> getSupportedOptions()
	{
		return EmptySupportedOptions;
	}

	@Override
	@NotNull
	public final SourceVersion getSupportedSourceVersion()
	{
		return RELEASE_7;
	}

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@NotNull
	@Override
	public final Set<String> getSupportedAnnotationTypes()
	{
		return AllCode;
	}

	@Override
	public final void init(@NotNull final ProcessingEnvironment processingEnv)
	{
		messager = processingEnv.getMessager();
		typeUtilities = processingEnv.getTypeUtils();
		elementUtilities = processingEnv.getElementUtils();
		// Can also be obtained from the CompilationTask
		trees = instance(processingEnv);
	}

	@NotNull
	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@Override
	public final Iterable<? extends Completion> getCompletions(@Nullable final Element element, @Nullable final AnnotationMirror annotation, @Nullable final ExecutableElement member, @Nullable final String userText)
	{
		return EmptyCompletions;
	}

	@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
	@Override
	public abstract boolean process(@NotNull final Set<? extends TypeElement> annotations, @NotNull final RoundEnvironment roundEnv);
}

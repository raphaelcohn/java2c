package com.stormmq.java2c.transpiler.conversion.elementConverters;

import com.stormmq.java2c.transpiler.conversion.CMaker;
import com.stormmq.java2c.transpiler.conversion.c.VariableDeclaration;
import com.stormmq.java2c.transpiler.warnings.Warnings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;
import static javax.lang.model.element.ElementKind.*;

public final class TopLevelClassElementConverter implements ElementConverter<TypeElement>
{
	@NotNull private final CMaker cMaker;

	public TopLevelClassElementConverter(@NotNull final CMaker cMaker)
	{
		this.cMaker = cMaker;
	}

	@Override
	public void convert(@NotNull final Warnings warnings, @NotNull final TypeElement element) throws ConversionException
	{
		// No name for local and anonymous classes, which is annoying
		final CategorisedClassMembers categorisedClassMembers = new CategorisedClassMembers(element, element.getQualifiedName());

		final List<String> libraryIncludes = new ArrayList<>(16);

		final List<? extends Element> enclosedElements = element.getEnclosedElements();
		for (Element enclosedElement : enclosedElements)
		{
			final ElementKind kind = enclosedElement.getKind();

			if (kind == INSTANCE_INIT || kind == STATIC_INIT)
			{
				final ExecutableElement initializer = (ExecutableElement) enclosedElement;
				System.out.println("initializer = " + initializer);
			}
			else if (kind == FIELD)
			{
				// Are we final? Instance or static?

				final VariableElement field = (VariableElement) enclosedElement;

				// A primitive or a string, not null ONLY if the field is (a) final and (b) evaluates to a given value
				// Can be used to determine whether to use C's const (although strings are a problem, as we're probably going to hide them away)
				@Nullable final Object constantValue = field.getConstantValue();

				System.out.println("constantValue = " + constantValue);

				// In C land, for static variables, we should probably assign NULL to be safe...

				final TypeMirror typeMirror = field.asType();
				final TypeKind typeKind = typeMirror.getKind();
				if (typeKind.isPrimitive())
				{
					// replace with [u] int[8 - 64], float or double
				}

				// Deprecated, unsigned are wanted!
				final List<? extends AnnotationMirror> annotationMirrors = field.getAnnotationMirrors();
				for (AnnotationMirror annotationMirror : annotationMirrors)
				{
					final DeclaredType annotationType = annotationMirror.getAnnotationType();
					System.out.println("annotationType = " + annotationType);
				}

				final Set<Modifier> modifiers = field.getModifiers(); // eg public static final => extern const
				System.out.println("modifiers = " + modifiers);
			}
			// METHOD, CONSTRUCTOR also possible...
		}

		try
		{
			cMaker.makeFiles(element, true, Collections.<String>emptyList(), Collections.<String>emptyList(), Collections.<VariableDeclaration>emptyList());
		}
		catch (IOException e)
		{
			throw new ConversionException(format(ENGLISH, "Could not create header or source files for %1$s", element.getQualifiedName().toString()), e);
		}
	}
}

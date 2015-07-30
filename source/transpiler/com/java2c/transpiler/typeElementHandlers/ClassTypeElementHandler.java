package com.java2c.transpiler.typeElementHandlers;

import com.compilerUser.elementHandlers.typeElementHandlers.ModifierValidator;
import com.compilerUser.elementHandlers.typeElementHandlers.TypeElementHandler;
import com.java2c.model.types.scalars.*;
import com.compilerUser.elementHandlers.AbstractSyntaxTreeInterpreter;
import com.compilerUser.elementHandlers.ElementHandler;
import com.java2c.transpiler.OurAbstractSyntaxTreeInterpreter;
import com.java2c.transpiler.exceptions.IncorrectSourceCodeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.*;
import java.util.List;

import static com.compilerUser.text.EnglishFormatter.format;
import static javax.lang.model.element.Modifier.*;

public final class ClassTypeElementHandler implements TypeElementHandler<OurAbstractSyntaxTreeInterpreter>
{
	@Override
	public void handle(@NotNull final OurAbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement, OurAbstractSyntaxTreeInterpreter> dispatchingTypeElementHandler)
	{
		abstractSyntaxTreeInterpreter.guardClassInheritsFromAbstractCType(element);
		if (abstractSyntaxTreeInterpreter.isSuperclassAbstractScalar(element))
		{
			handleAbstractScalarClass(abstractSyntaxTreeInterpreter, element, dispatchingTypeElementHandler);
		}
		else
		{
			handleClass(abstractSyntaxTreeInterpreter, element, dispatchingTypeElementHandler);
		}
	}

	@NotNull
	private static final ModifierValidator ScalarIsFinalModifierValidator = new ModifierValidator(new Modifier[]{FINAL}, ABSTRACT, PRIVATE, PROTECTED, NATIVE, STRICTFP, SYNCHRONIZED, TRANSIENT, VOLATILE);

	private void handleAbstractScalarClass(@NotNull final OurAbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement, OurAbstractSyntaxTreeInterpreter> dispatchingTypeElementHandler)
	{
		@Nullable final Scalar scalar = element.getAnnotation(Scalar.class);
		if (scalar == null)
		{
			throw new IncorrectSourceCodeException(format("The class '1$%s' must have a @Scalar annotation because it inherits from AbstractScalar", abstractSyntaxTreeInterpreter.getCanonicalClassName(element)));
		}

		ScalarIsFinalModifierValidator.validate(abstractSyntaxTreeInterpreter, element);

		// Must contain only one constructor taking long, except for constructor that take other values, eg
		xxxx;

		final List<? extends Element> enclosedElements = element.getEnclosedElements();
		for (final Element enclosedElement : enclosedElements)
		{
			if (enclosedElement instanceof TypeElement)
			{
				dispatchingTypeElementHandler.handle(abstractSyntaxTreeInterpreter, (TypeElement) enclosedElement);
			}
			else if (enclosedElement instanceof VariableElement)
			{
				// static field or instance field, but a field nonetheless
			}
			else if (enclosedElement instanceof ExecutableElement)
			{
				final ExecutableElement executableElement = (ExecutableElement) enclosedElement;
				abstractSyntaxTreeInterpreter.guardHasCCodeTemplateAndIsBanishedAreMutuallyExclusive(executableElement);

				// Must only be one constructor taking long

				// initialiser blocks are painful, aren't they?

				// if method has @CCodeTemplate, don't generate...


				if(abstractSyntaxTreeInterpreter.isBanished(executableElement))
				{

				}
				else
				{
					if (;)
				}

				/*
					for each method that is not banished
						- check it's entire call chain for any use of banished code
				 */
				// does it have a CCodeTemplate? [does this matter at this point]?
			}
			else
			{
				throw new IllegalStateException(format("Did not expect an enclosed element '%1$s'", enclosedElement));
			}
		}
	}

	private void handleClass(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element, @NotNull final ElementHandler<TypeElement> dispatchingTypeElementHandler)
	{
		// TODO: Must not have AbstractScalar as any superclass
	}


	public interface ExecutableElementHandler
	{
		void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement containingClassEtc, @NotNull final ElementHandler<TypeElement> dispatchingTypeElementHandler, @NotNull final ExecutableElement executableElement);
	}

	public static final class ScalarExecutableElementHandler implements ExecutableElementHandler
	{
		@Override
		public void handle(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement containingClassEtc, @NotNull final ElementHandler<TypeElement> dispatchingTypeElementHandler, @NotNull final ExecutableElement executableElement)
		{
		}
	}
}

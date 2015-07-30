package com.compilerUser.elementHandlers.typeElementHandlers;

import com.compilerUser.elementHandlers.AbstractSyntaxTreeInterpreter;
import com.compilerUser.exceptions.IncorrectSourceCodeException;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static com.compilerUser.text.EnglishFormatter.format;
import static java.util.Collections.addAll;
import static java.util.EnumSet.noneOf;

public final class ModifierValidator
{
	@NotNull
	private static final Modifier[] NoRequiredModifiers = {};

	@NotNull
	private final Set<Modifier> requiredModifiers;

	@NotNull
	private final Set<Modifier> modifiersNotAllowed;

	public ModifierValidator(@NotNull final Modifier... modifiersNotAllowed)
	{
		this(NoRequiredModifiers, modifiersNotAllowed);
	}

	public ModifierValidator(@NotNull final Modifier[] requiredModifiers, @NotNull final Modifier... modifiersNotAllowed)
	{
		this.requiredModifiers = noneOf(Modifier.class);
		addAll(this.requiredModifiers, requiredModifiers);

		this.modifiersNotAllowed = noneOf(Modifier.class);
		addAll(this.modifiersNotAllowed, modifiersNotAllowed);
	}

	public void validate(@NotNull final AbstractSyntaxTreeInterpreter abstractSyntaxTreeInterpreter, @NotNull final TypeElement element)
	{
		final Set<Modifier> modifiers = element.getModifiers();

		for (final Modifier requiredModifier : requiredModifiers)
		{
			if (!modifiers.contains(requiredModifier))
			{
				throw new IncorrectSourceCodeException(format("The type '1$%s' does not have the required modifier '%2$s'", abstractSyntaxTreeInterpreter.getCanonicalClassName(element), requiredModifier.name()));
			}
		}
		
		for (final Modifier modifierNotAllowed : modifiersNotAllowed)
		{
			if (!modifiers.contains(modifierNotAllowed))
			{
				throw new IncorrectSourceCodeException(format("The type '1$%s' has the disallowed modifier '%2$s'", abstractSyntaxTreeInterpreter.getCanonicalClassName(element), modifierNotAllowed.name()));
			}
		}
	}
}

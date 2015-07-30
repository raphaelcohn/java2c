package com.compilerUser.processors;

import com.compilerUser.codeTreeUsers.CodeTreeUser;
import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public final class CodeTreeUserAdaptingProcessor extends AbstractProcessor
{
	@NotNull
	private final CodeTreeUser codeTreeUser;

	public CodeTreeUserAdaptingProcessor(@NotNull final CodeTreeUser codeTreeUser)
	{
		this.codeTreeUser = codeTreeUser;
	}

	@Override
	public boolean process(@NotNull final Set<? extends TypeElement> annotations, @NotNull final RoundEnvironment roundEnv)
	{
		assert messager != null;
		assert typeUtilities != null;
		assert elementUtilities != null;
		assert trees != null;

		codeTreeUser.process(messager, typeUtilities, elementUtilities, trees, roundEnv.getRootElements());

		return true;
	}
}

package com.compilerUser.codeTreeUsers;

import com.sun.source.util.Trees;
import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

public interface CodeTreeUser
{
	void process(@NotNull final Messager messager, @NotNull final Types typeUtilities, @NotNull final Elements elementUtilities, @NotNull final Trees trees, @NotNull final Set<? extends Element> rootElements);
}

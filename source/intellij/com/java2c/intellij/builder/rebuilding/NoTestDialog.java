package com.java2c.intellij.builder.rebuilding;

import com.intellij.openapi.ui.TestDialog;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.ui.Messages.NO;

public final class NoTestDialog implements TestDialog
{
	@NotNull
	public static final TestDialog AnswerAlwaysAsNo = new NoTestDialog();

	private NoTestDialog()
	{
	}

	@Override
	public int show(final String message)
	{
		return NO;
	}
}

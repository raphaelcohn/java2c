package com.intellij.idea;

import com.intellij.idea.StartupUtil.AppStarter;
import com.intellij.openapi.application.ApplicationStarter;
import com.intellij.openapi.extensions.ExtensionPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.intellij.ExtensionPoints.APPLICATION_STARTER;
import static com.intellij.ide.plugins.PluginManager.installExceptionHandler;
import static com.intellij.ide.plugins.PluginManagerCore.getPlugins;
import static com.intellij.idea.StartupUtil.prepareAndStart;
import static com.intellij.openapi.extensions.Extensions.getRootArea;
import static com.intellij.util.PlatformUtils.*;
import static java.lang.Class.forName;
import static java.lang.String.format;
import static java.lang.System.setProperty;
import static java.util.Locale.ENGLISH;
import static javax.swing.SwingUtilities.invokeLater;

@SuppressWarnings({"UnusedDeclaration", "UtilityClass"})
public final class MainImplReplacement
{
	/**
	 * Called from PluginManager via reflection.
	 */
	@SuppressWarnings("AccessOfSystemProperties")
	public static void start(@NotNull final String... commandLineArguments)
	{
		if (commandLineArguments.length == 0)
		{
			throw new IllegalStateException("commandLineArguments must contain at least a command");
		}
		setProperty(PLATFORM_PREFIX_KEY, getPlatformPrefix(IDEA_CE_PREFIX));

		prepareAndStart(commandLineArguments, new AppStarter()
		{
			@Override
			public void start(final boolean newConfigFolder)
			{
				invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						installExceptionHandler();


						final IdeaApplication ideaApplication = new IdeaApplication(commandLineArguments)
						{
							@NotNull
							@Override
							public ApplicationStarter getStarter()
							{
								final String commandName = commandLineArguments[0];
								assert commandName != null;
								final ApplicationStarter ourApplication = createApplicationStarter(commandName, getClass().getClassLoader());
								registerApplicationStarter(ourApplication);

								return super.getStarter();
							}
						};
						invokeLater(new Runnable()
						{
							@Override
							public void run()
							{
								ideaApplication.run();
							}
						});
					}
				});
			}
		});
	}

	@NotNull
	@SuppressWarnings({"unchecked", "MethodOnlyUsedFromInnerClass"})
	private static ApplicationStarter createApplicationStarter(@NotNull final String className, @Nullable final ClassLoader classLoader)
	{
		final Class<? extends ApplicationStarter> clazz;
		try
		{
			clazz = (Class<? extends ApplicationStarter>) forName(className, true, classLoader);
		}
		catch (final ClassNotFoundException e)
		{
			throw new IllegalStateException(format(ENGLISH, "Could not load class '%1$s'", className), e); //NON-NLS
		}
		assert clazz != null;

		final Constructor<? extends ApplicationStarter> constructor;
		try
		{
			constructor = clazz.getConstructor();
		}
		catch (final NoSuchMethodException e)
		{
			throw new IllegalStateException(couldNotInstantiateClass(className), e);
		}

		try
		{
			assert constructor != null;
			return constructor.newInstance();
		}
		catch (final IllegalAccessException e)
		{
			throw new IllegalStateException(couldNotInstantiateClass(className), e);
		}
		catch (final InstantiationException e)
		{
			throw new IllegalStateException(couldNotInstantiateClass(className), e);
		}
		catch (final InvocationTargetException e)
		{
			throw new IllegalStateException(couldNotInstantiateClass(className), e);
		}
	}

	@SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
	@NotNull
	private static String couldNotInstantiateClass(@NotNull final String className)
	{
		final String format = format(ENGLISH, "Could not instantiate class '%1$s'", className); // NON-NLS
		assert format != null;
		return format;
	}

	@SuppressWarnings("MethodOnlyUsedFromInnerClass")
	private static void registerApplicationStarter(@NotNull final ApplicationStarter ourApplication)
	{
		// When this is called, it wipes out the registration of all ExtensionPoints
		getPlugins();

		final ExtensionPoint<ApplicationStarter> extensionPoint = getRootArea().getExtensionPoint(APPLICATION_STARTER);
		extensionPoint.registerExtension(ourApplication);
	}

	private MainImplReplacement()
	{
	}
}

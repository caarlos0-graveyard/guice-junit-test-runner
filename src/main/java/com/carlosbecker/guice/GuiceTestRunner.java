package com.carlosbecker.guice;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceTestRunner extends BlockJUnit4ClassRunner {
	private Injector injector;

	public GuiceTestRunner(Class<?> klass) throws InitializationError,
			InstantiationException, IllegalAccessException {
		super(klass);
		injector = createInjectorFor(getModulesFor(klass));
	}

	@Override
	public Object createTest() throws Exception {
		Object obj = super.createTest();
		injector.injectMembers(obj);
		return obj;
	}

	private Injector createInjectorFor(Class<?>[] classes)
			throws InitializationError, InstantiationException,
			IllegalAccessException {
		return createInjector(classes);
	}

	private Injector createInjector(Class<?>[] classes)
			throws InstantiationException, IllegalAccessException {
		Module[] modules = new Module[classes.length];
		for (int i = 0; i < classes.length; i++)
			modules[i] = (Module) (classes[i]).newInstance();
		return Guice.createInjector(modules);
	}

	private Class<?>[] getModulesFor(Class<?> klass) throws InitializationError {
		GuiceModules annotation = klass.getAnnotation(GuiceModules.class);
		if (annotation == null)
			throw new InitializationError(String.format(
					"Missing @GuiceModules annotation for unit test '%s'",
					klass.getName()));
		return annotation.value();
	}
}

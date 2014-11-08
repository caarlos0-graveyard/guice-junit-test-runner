package com.carlosbecker.guice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.model.InitializationError;

import com.carlosbecker.guice.doubles.FullTestClassExample;
import com.carlosbecker.guice.doubles.WithoutGuiceModulesTestClassExample;

public class GuiceTestRunnerTest {
	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void testFullClass() throws Exception {
		new GuiceTestRunner(new FullTestClassExample().getClass());
	}

	@Test
	public void testMissgingGuiceModules() throws Exception {
		expected.expect(InitializationError.class);
		new GuiceTestRunner(
				new WithoutGuiceModulesTestClassExample().getClass());
	}
}

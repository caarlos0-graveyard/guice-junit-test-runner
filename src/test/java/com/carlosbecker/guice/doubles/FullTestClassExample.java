package com.carlosbecker.guice.doubles;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;

@GuiceModules(TestModule.class)
@RunWith(GuiceTestRunner.class)
public class FullTestClassExample {
	@Test
	public void testName() throws Exception {

	}
}

package com.carlosbecker.guice;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.carlosbecker.guice.doubles.MyWorker;
import com.carlosbecker.guice.doubles.TestModule;
import com.carlosbecker.guice.doubles.Worker;

@RunWith(GuiceTestRunner.class)
@GuiceModules(TestModule.class)
public class JUnitTestRunnerTest {
	@Inject
	private Worker worker;

	@Test
	public void testWorkerBinded() throws Exception {
		assertThat(worker, notNullValue());
	}

	@Test
	public void testCorrectInstance() throws Exception {
		assertThat(worker, instanceOf(MyWorker.class));
	}

	@Test
	public void testMethodCall() throws Exception {
		worker.work();
	}
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Carlos Alexandro Becker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.carlosbecker.guice.tests;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.carlosbecker.guice.tests.doubles.MyWorker;
import com.carlosbecker.guice.tests.doubles.TestModule;
import com.carlosbecker.guice.tests.doubles.Worker;
import javax.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test for GuiceTestRunner.
 *
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 * @version $Id$
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules(TestModule.class)
public class GuiteTestRunnerIntegrationTest {
    /**
     * Injected worker instance.
     */
    @Inject
    private Worker worker;

    /**
     * Verify that the instance is not null.
     * @throws Exception If something goes wrong.
     */
    @Test
    public final void testWorkerBinded() throws Exception {
        Assert.assertThat(this.worker, CoreMatchers.notNullValue());
    }

    /**
     * Verify that the instance is of the correct implementation.
     * @throws Exception If something goes wrong.
     */
    @Test
    public final void testCorrectInstance() throws Exception {
        Assert.assertThat(this.worker, CoreMatchers.instanceOf(MyWorker.class));
    }

    /**
     * Call the injected instance method.
     * @throws Exception If something goes wrong.
     */
    @Test
    public final void testMethodCall() throws Exception {
        this.worker.work();
    }
}

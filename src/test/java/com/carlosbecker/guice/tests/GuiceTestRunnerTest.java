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

import com.carlosbecker.guice.GuiceTestRunner;
import com.carlosbecker.guice.tests.doubles.FullTestClassExample;
import com.carlosbecker.guice.tests.doubles.NoGuiceModulesExample;
import com.carlosbecker.guice.tests.doubles.PrivateConstructorExample;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.model.InitializationError;

/**
 * Unit tests for GuiceTestRunner.
 *
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 * @version $Id$
 */
public class GuiceTestRunnerTest {
    /**
     * Expected exceptions.
     * @checkstyle VisibilityModifierCheck (5 lines)
     */
    @Rule
    public transient ExpectedException expected = ExpectedException.none();

    /**
     * Assure that a full configured class can be tested.
     * @throws Exception If anything goes wrong.
     */
    @Test
    public final void testFullClass() throws Exception {
        new GuiceTestRunner(FullTestClassExample.class);
    }

    /**
     * Assure that a class without the GuiceModules annotation fails.
     * @throws Exception If anything goes wrong.
     */
    @Test
    public final void testMissgingGuiceModules() throws Exception {
        this.expected.expect(InitializationError.class);
        new GuiceTestRunner(NoGuiceModulesExample.class);
    }

    /**
     * Assure that a class without the GuiceModules annotation fails.
     * @throws Exception If anything goes wrong.
     */
    @Test
    public final void testPrivateConstructorModule() throws Exception {
        this.expected.expect(InitializationError.class);
        new GuiceTestRunner(PrivateConstructorExample.class);
    }
}

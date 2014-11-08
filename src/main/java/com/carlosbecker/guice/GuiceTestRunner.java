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
package com.carlosbecker.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * The GuiceTestRunner main class. Use it as:
 *
 * <pre>
 * &#064;RunWith(GuiceTestRunner.class)
 * public class MyTest {
 *     &#064;Inject
 *     private Worker worker;
 *
 *     &#064;Test
 *     public void testSomething() {
 *         Assert.assertThat(this.worker.work(), CoreMatchers.notNullValue());
 *     }
 * }
 * </pre>
 *
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 * @version $Id$
 */
public class GuiceTestRunner extends BlockJUnit4ClassRunner {
    /**
     * The Guice Injector.
     */
    private final transient Injector injector;

    /**
     * Contructor.
     *
     * @param klass The in test.
     * @throws InitializationError If something goes wrong.
     * @checkstyle ThrowsCountCheck
     */
    public GuiceTestRunner(final Class<?> klass)
        throws InitializationError {
        super(klass);
        this.injector = this.createInjectorFor(this.getModulesFor(klass));
    }

    @Override
    public final Object createTest() throws Exception {
        final Object obj = super.createTest();
        this.injector.injectMembers(obj);
        return obj;
    }

    /**
     * Create a Guice Injector for the class under test.
     * @param classes Guice Modules
     * @return A Guice Injector instance.
     * @throws InitializationError If couldn't instantiate a module.
     */
    private Injector createInjectorFor(final Class<?>[] classes)
        throws InitializationError {
        final List<Module> modules = new ArrayList<Module>(classes.length);
        for (final Class<?> module : Arrays.asList(classes)) {
            try {
                modules.add((Module) module.newInstance());
            } catch (final ReflectiveOperationException exception) {
                throw new InitializationError(exception);
            }
        }
        return Guice.createInjector(modules);
    }

    /**
     * Get the list of Guice Modules request by GuiceModules annotation in the
     * class under test.
     * @param klass Class under test.
     * @return A Class Array of Guice Modules required by this class.
     * @throws InitializationError If the annotation is not present.
     */
    private Class<?>[] getModulesFor(final Class<?> klass)
        throws InitializationError {
        final GuiceModules annotation = klass.getAnnotation(GuiceModules.class);
        if (annotation == null) {
            final String message = String.format(
                "Missing @GuiceModules annotation for unit test '%s'",
                klass.getName()
            );
            throw new InitializationError(message);
        }
        return annotation.value();
    }
}

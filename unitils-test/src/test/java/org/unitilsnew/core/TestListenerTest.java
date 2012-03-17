/*
 * Copyright 2012,  Unitils.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.unitilsnew.core;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.mock.annotation.Dummy;

import static org.junit.Assert.assertEquals;
import static org.unitilsnew.core.TestPhase.EXECUTION;

/**
 * @author Tim Ducheyne
 */
public class TestListenerTest extends UnitilsJUnit4 {

    /* Tested object */
    private TestListener testListener;

    @Dummy
    private TestClass testClass;
    @Dummy
    private TestInstance testInstance;


    @Before
    public void initialize() throws Exception {
        testListener = new MyTestListener();
    }


    @Test
    public void testPhaseDefaultsToExecutionPhase() {
        TestPhase result = testListener.getTestPhase();
        assertEquals(EXECUTION, result);
    }

    @Test
    public void defaultBeforeTestClassDoesNothing() {
        testListener.beforeTestClass(testClass);
    }

    @Test
    public void defaultBeforeTestSetUpDoesNothing() {
        testListener.beforeTestSetUp(testInstance);
    }

    @Test
    public void defaultBeforeTestMethodDoesNothing() {
        testListener.beforeTestMethod(testInstance);
    }

    @Test
    public void defaultAfterTestMethodDoesNothing() {
        NullPointerException e = new NullPointerException();
        testListener.afterTestMethod(testInstance, e);
    }

    @Test
    public void defaultAfterTestTearDownDoesNothing() {
        testListener.afterTestTearDown(testInstance);
    }


    private static class MyTestListener extends TestListener {
    }
}
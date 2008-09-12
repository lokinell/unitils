/*
 * Copyright 2008,  Unitils.org
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
package org.unitils.mock.argumentmatcher.impl;

import org.unitils.mock.argumentmatcher.ArgumentMatcher;

/**
 * A matcher for checking whether an argument equals a given value.
 *
 * @author Kenny Claes
 * @author Filip Neven
 * @author Tim Ducheyne
 */
public class EqualsArgumentMatcher implements ArgumentMatcher {

    /* The excepted value */
    private final Object value;


    /**
     * Creates a matcher for the given value.
     *
     * @param value The expected value
     */
    public EqualsArgumentMatcher(Object value) {
        this.value = value;
    }


    /**
     * Returns true if the given object matches the expected argument, false otherwise.
     *
     * @param value The value to match
     * @return True when passed object matches, false otherwise.
     */
    public boolean matches(Object value) {
        if (this.value == null) {
            return value == null;
        }
        return this.value.equals(value);
    }
}
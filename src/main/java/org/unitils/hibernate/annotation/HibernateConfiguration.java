/*
 * Copyright 2006 the original author or authors.
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
package org.unitils.hibernate.annotation;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * Annotation indicating that this method creates and returns an instance of the <code>org.hibernate.cfg.Configuration</code>
 * The annotated method should one or more hibernate configuration files and optionally do some extra programmatic Hibernate
 * configuration, such as registering mapped classes. The returned configuration will be manipulated afterwards to make
 * sure the <code>DataSource</code> provided by the {@link org.unitils.database.DatabaseModule} is used by Hibernate.
 * The method should have following signature: <code>org.hibernate.Configuration myMethod()</code>
 *
 * @author Filip Neven
 * @author Tim Ducheyne
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface HibernateConfiguration {
}
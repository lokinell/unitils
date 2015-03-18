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
package org.unitils.orm.jpa;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Transactional(TransactionMode.ROLLBACK)
public class HibernateJpaSpringTest extends UnitilsJUnit4 {

	@SpringApplicationContext({"org/unitils/orm/jpa/hibernateJpaSpringTest-spring.xml"})
	ApplicationContext applicationContext;
	
	@PersistenceContext
	EntityManager entityManager;
	

    @Test
    public void hello(){

    }
	
}
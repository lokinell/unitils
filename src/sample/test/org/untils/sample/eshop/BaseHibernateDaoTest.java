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
package org.untils.sample.eshop;

import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.Inject;
import org.unitils.sample.eshop.dao.HibernateSessionManager;
import org.unitils.sample.eshop.dao.DiscountDao;
import org.unitils.hibernate.annotation.HibernateTest;
import org.unitils.hibernate.annotation.HibernateSession;
import org.unitils.hibernate.annotation.HibernateConfiguration;
import org.unitils.hibernate.annotation.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * 
 */
@HibernateTest
public abstract class BaseHibernateDaoTest extends UnitilsJUnit3 {

    @HibernateSessionFactory
    @Inject(property = "sessionFactory")
    private SessionFactory hibernateSessionFactory;

    @HibernateConfiguration
    private Configuration createHibernateConfiguration() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/eshop-config.xml");
        LocalSessionFactoryBean sessionFactoryBean = (LocalSessionFactoryBean)
                applicationContext.getBean("&sessionFactoryBean");
        return sessionFactoryBean.getConfiguration();
    }

}
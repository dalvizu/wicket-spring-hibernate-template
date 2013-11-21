/**
 * Copyright 2013 Dan Alvizu (alvizu@gmail.com)
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
package com.pingone.fuji.web.spring;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.pingone.fuji.web.FujiWebApplication;
import com.pingone.fuji.web.dao.UserDao;
import com.pingone.fuji.web.dao.UserDaoImpl;
import com.pingone.fuji.web.svc.BasicSvc;
import com.pingone.fuji.web.svc.BasicSvcImpl;

/**
 * 
 * @author dalvizu
 */
@Configuration
@ImportResource(value = {"classpath:/com/pingone/fuji/web/spring-tx.xml"})
public class WebConfig
{
    
    @Bean
    public FujiWebApplication fujiWebApplication()
    {
        return new FujiWebApplication();
    }
    
    @Bean
    public BasicSvc fujiSvc()
    {
        return new BasicSvcImpl();
    }
    
    @Bean
    public UserDao userDao()
    {
        return new UserDaoImpl();
    }
    
    @Bean
    public SessionFactory sessionFactory()
    {
        try
        {
            final Properties properties = new Properties();
            properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            properties.put("hibernate.hbm2ddl.auto", "create");
            final AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
            asfb.setDataSource(dataSource("jdbc/webdb"));
            asfb.setPackagesToScan(new String[] {"com.pingone.fuji.web.dao"});
            asfb.setHibernateProperties(properties);
            asfb.afterPropertiesSet();
            return asfb.getObject();
        }
        catch (final Exception x)
        {
            throw new RuntimeException(x);
        }
    }
    
    @Bean
    public PlatformTransactionManager transactionManager()
    {
        final HibernateTransactionManager xam = new HibernateTransactionManager();
        xam.setSessionFactory(sessionFactory());
        xam.afterPropertiesSet();
        return xam;
    }

    @Bean
    public TransactionTemplate transactionTemplate() 
    {
        return new TransactionTemplate(transactionManager());
    }
    
    protected DataSource dataSource(final String jndiName)
        throws NamingException
    {
        final JndiObjectFactoryBean jofb = new JndiObjectFactoryBean();
        jofb.setJndiName(jndiName);
        jofb.setProxyInterface(javax.sql.DataSource.class);
        jofb.setResourceRef(true);
        jofb.afterPropertiesSet();
        return (DataSource) jofb.getObject();
    }
}
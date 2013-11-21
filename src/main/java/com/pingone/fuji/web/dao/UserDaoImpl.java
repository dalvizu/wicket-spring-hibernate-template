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
package com.pingone.fuji.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dalvizu
 */
public class UserDaoImpl
    implements UserDao
{

    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public User findByEmail(String email)
    {
        return (User)getSession().createQuery("from User where email=:email").setString("email", email)
                .uniqueResult();
    }

    @Override
    public User findById(Long id)
    {
        return (User)getSession().createQuery("from User where id=:id").setLong("id", id)
                .uniqueResult();
    }

    @Override
    public void saveUser(User user)
    {
        getSession().saveOrUpdate(user);
    }

}
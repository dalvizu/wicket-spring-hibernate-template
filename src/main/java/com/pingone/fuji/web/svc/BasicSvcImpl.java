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
package com.pingone.fuji.web.svc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pingone.fuji.web.dao.User;
import com.pingone.fuji.web.dao.UserDao;
import com.pingone.fuji.web.dto.UserDto;

public class BasicSvcImpl
    implements BasicSvc
{
    @Autowired
    private UserDao userDao;
    
    @Override
    public String getCurrentDate()
    {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date());
    }
    
    @Transactional
    @Override
    public boolean isEmailTakenByAUser(String email)
    {
        return userDao.findByEmail(email) != null;
    }

    @Override
    public void saveUser(UserDto userDto)
    {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userDao.saveUser(user);
    }

    @Override
    public UserDto getUser(String email)
    {
        User user = userDao.findByEmail(email);
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword()); // hashing passwords is hard - what are we, a digital identity company?
        userDto.setSpamBot(false);
        return userDto;
    }
}

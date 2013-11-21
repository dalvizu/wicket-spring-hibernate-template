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
package com.pingone.fuji.web.dto;

import java.io.Serializable;

/**
 * @author dalvizu
 *
 */
public class UserDto
    implements Serializable
{
    
    private static final long serialVersionUID = -5076499823064937588L;

    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private boolean spamBot;
    
    private String password;
    
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isSpamBot()
    {
        return spamBot;
    }
    
    public void setSpamBot(boolean spamBot)
    {
        this.spamBot = spamBot;
    }

}

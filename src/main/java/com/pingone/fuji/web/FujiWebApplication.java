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
package com.pingone.fuji.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class FujiWebApplication 
    extends WebApplication
{
    
    public FujiWebApplication()
    {
        super();
    }
    
    @Override
    public Class<? extends Page> getHomePage()
    {
        return HomePage.class;
    }
    
    @Override
    protected void init()
    {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        mountPage("/home", HomePage.class);
        mountPage("/wicketdemo", WicketDemo.class);
    }
    
}
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

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.pingone.fuji.web.svc.BasicSvc;

/**
 * 
 * @author dalvizu
 */
public class WicketDemo
    extends WebPage
{
    private static final long serialVersionUID = -5936903709843445791L;

    @SpringBean
    private BasicSvc basicSvc;
    
    public WicketDemo()
    {
        super();
        add(new Label("currentDay", new AbstractReadOnlyModel<String>()
        {

            private static final long serialVersionUID = -9190170483114408204L;

            @Override
            public String getObject()
            {
                return basicSvc.getCurrentDate();
            }
        }));
        
        add(new Label("emailTaken", new AbstractReadOnlyModel<String>()
        {

            private static final long serialVersionUID = 394410077004796591L;

            @Override
            public String getObject()
            {
                return Boolean.toString(basicSvc.isEmailTakenByAUser("joe@example.com"));
            }
            
        }));
    }
    
}
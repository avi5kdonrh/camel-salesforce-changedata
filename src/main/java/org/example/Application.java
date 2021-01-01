/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.salesforce.SalesforceComponent;
import org.apache.camel.component.salesforce.SalesforceEndpointConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class Application  extends RouteBuilder {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "salesforce")
    public SalesforceComponent salesforce(){
        SalesforceComponent salesforceComponent = new SalesforceComponent();
        salesforceComponent.setClientId("");
        salesforceComponent.setClientSecret("");
        salesforceComponent.setUserName("");
        salesforceComponent.setLoginUrl("https://login.salesforce.com");
        salesforceComponent.setPassword("");
        SalesforceEndpointConfig salesforceEndpointConfig = new SalesforceEndpointConfig();
        salesforceEndpointConfig.setApiVersion("45.0");
        salesforceComponent.setConfig(salesforceEndpointConfig);
        return salesforceComponent;
    }

    @Override
    public void configure() throws Exception {
        from("salesforce:data/AccountChangeEvent?replayId=-1")
                .log("The change data event message is :: ${body}");
    }
}
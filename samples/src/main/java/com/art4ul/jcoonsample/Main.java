/*
 * Copyright (C) 2014 Artsem Semianenka (http://art4ul.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.art4ul.jcoonsample;

import com.art4ul.jcoonsample.client.service.TestService;
import com.art4ul.jcoonsample.server.ServerLauncher;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Artsem_Semianenka on 11/14/2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        WebApplicationContext applicationContext = ServerLauncher.getContext(); // Receive Spring Application context
        ServerLauncher serverLauncher = new ServerLauncher();                   // Create object of HTTP Server launcher (Jetty server)
        try {
            serverLauncher.startServer(applicationContext);                         // Start Http server (just to handle our REST requests )

            TestService service = applicationContext.getBean(TestService.class);    // Receive test bean
            service.doTest();                                                       // Invoke test method from it
        } finally {
            serverLauncher.stopServer();                                             // Stop HTTP server
        }
    }


}

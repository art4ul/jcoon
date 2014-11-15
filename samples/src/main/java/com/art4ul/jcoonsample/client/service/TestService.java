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

package com.art4ul.jcoonsample.client.service;

import com.art4ul.jcoon.annotations.RestClient;
import com.art4ul.jcoonsample.client.rest.ExampleRestClient;
import com.art4ul.jcoonsample.models.ResultModel;
import com.art4ul.jcoonsample.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Artsem_Semianenka on 11/14/2014.
 */

@Service
public class TestService {

    private static final Logger LOG = LoggerFactory.getLogger(TestService.class);

    @RestClient
    private ExampleRestClient restClient;

    public void doTest() {

        LOG.info("Send GET request ");
        ResultModel result = restClient.simpleGetRequestTest("http://localhost:8080", "world");
        LOG.info("simpleGetRequestTest return: {}", result);

        LOG.info("Send POST request ");
        UserModel userModel = new UserModel("Tester", "sam", "123456");
        result = restClient.simplePostRequestTest("http://localhost:9090", userModel);
        LOG.info("simpleGetRequestTest return: {}", result);


        restClient.setBaseUrl("http://localhost:8080");

    }
}

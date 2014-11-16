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
import com.art4ul.jcoonsample.client.exception.CustomException;
import com.art4ul.jcoonsample.client.rest.ExampleRestClient;
import com.art4ul.jcoonsample.models.ResultModel;
import com.art4ul.jcoonsample.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by Artsem_Semianenka on 11/14/2014.
 */

@Service
public class TestService {

    private static final Logger LOG = LoggerFactory.getLogger(TestService.class);

    @RestClient
    private ExampleRestClient restClient;

    public void doTest() {

        restClient.setBaseUrl("http://localhost:8080");

        LOG.info("Example #1: Send GET request");
        ResultModel result = restClient.exampleGetRequest("world");
        LOG.info("simpleGetRequestTest return: {} \n", result.getResult());


        LOG.info("Example #2: Send POST request with custom base URL");
        UserModel userModel = new UserModel("Tester", "sam", "123456");
        result = restClient.examplePostRequest("http://localhost:8080", userModel);
        LOG.info("simpleGetRequestTest return: {} \n", result.getResult());


        LOG.info("Example #3: Send POST request with custom HTTP header");
        result = restClient.examplePostRequestWithHeader(userModel, "test value");
        LOG.info("simpleGetRequestTest return: {} \n", result.getResult());

        LOG.info("Example #4: Send Get request with path variable");
        result = restClient.exampleGetRequestWithPathVariable("test");
        LOG.info("simpleGetRequestTest return: {} \n", result.getResult());

        LOG.info("Example #5: Handle HTTP exception");
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new CustomException("HTTP code: " + response.getStatusCode() + " text: " + response.getStatusText());
            }
        };
        try {
            result = restClient.exampleException("test", responseErrorHandler);
        } catch (CustomException ex) {
            LOG.info("Exception: " + ex.getMessage());
        }
    }
}

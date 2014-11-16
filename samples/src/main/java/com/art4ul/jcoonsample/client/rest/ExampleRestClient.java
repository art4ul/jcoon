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

package com.art4ul.jcoonsample.client.rest;

import com.art4ul.jcoon.annotations.BaseUrl;
import com.art4ul.jcoon.annotations.HttpErrorHandler;
import com.art4ul.jcoonsample.models.ResultModel;
import com.art4ul.jcoonsample.models.UserModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Created by artsemsemianenka on 10/15/14.
 */
@RequestMapping(value = "example1", headers = "Header2 = value2")
public interface ExampleRestClient {

    @BaseUrl
    void setBaseUrl(String url);

    @RequestMapping(value = "/exampleGet", method = RequestMethod.GET)
    ResultModel exampleGetRequest(@RequestParam("name") String name);

    @RequestMapping(value = "/examplePost", method = RequestMethod.POST)
    ResultModel examplePostRequest(@RequestBody UserModel user);

    @RequestMapping(value = "/examplePost", method = RequestMethod.POST)
    ResultModel examplePostRequest(@BaseUrl String baseUrl, @RequestBody UserModel user);

    @RequestMapping(value = "/examplePostWithHeader", method = RequestMethod.POST, headers = "Header2 = value2")
    ResultModel examplePostRequestWithHeader(@RequestBody UserModel user, @RequestHeader("myHeader") String header);

    @RequestMapping(value = "/{userId}/examplePostWithPathVariable", method = RequestMethod.GET)
    ResultModel exampleGetRequestWithPathVariable(@PathVariable("userId") String userId);

    @RequestMapping(value = "/exampleException", method = RequestMethod.GET)
    ResultModel exampleException(@RequestParam("name") String name, @HttpErrorHandler ResponseErrorHandler errorHandler);
}

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
import com.art4ul.jcoonsample.models.ResultModel;
import com.art4ul.jcoonsample.models.UserModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by artsemsemianenka on 10/15/14.
 */
@RequestMapping("example1")
public interface ExampleRestClient {

    @BaseUrl
    void setBaseUrl(String url);

    @RequestMapping(value = "/simpleGet", method = RequestMethod.GET)
    ResultModel simpleGetRequestTest(@RequestParam("name") String name);

    @RequestMapping(value = "/simplePost", method = RequestMethod.POST)
    ResultModel simplePostRequestTest(@RequestBody UserModel user);

    //

    @RequestMapping(value = "/simpleGet", method = RequestMethod.GET)
    ResultModel simpleGetRequestTest(@BaseUrl String baseUrl, @RequestParam("name") String name);

    @RequestMapping(value = "/simplePost", method = RequestMethod.POST)
    ResultModel simplePostRequestTest(@BaseUrl String baseUrl, @RequestBody UserModel user);

}

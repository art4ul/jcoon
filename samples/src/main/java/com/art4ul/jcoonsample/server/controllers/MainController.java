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

package com.art4ul.jcoonsample.server.controllers;

import com.art4ul.jcoonsample.models.ResultModel;
import com.art4ul.jcoonsample.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Artsem_Semianenka on 11/14/2014.
 */
@Controller
@RequestMapping("/example1")
public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/simpleGet", method = RequestMethod.GET)
    @ResponseBody
    public ResultModel simpleGetRequestTest(@RequestParam("name") String name) {
        LOG.info("simpleGetRequestTest(name = [{}])", name);
        // some business logic
        return new ResultModel("hello " + name, "simpleGetRequestTest method");
    }


    @RequestMapping(value = "/simplePost", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResultModel simplePostRequestTest(@RequestBody UserModel user) {
        LOG.info("simplePostRequestTest(user = [{}])", user);
        // some business logic
        return new ResultModel("User with login " + user.getLogin() + " is created.", "simplePostRequestTest method");
    }
}

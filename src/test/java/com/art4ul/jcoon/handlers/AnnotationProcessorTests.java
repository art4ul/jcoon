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

package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.rest.BaseUrl;
import com.art4ul.jcoon.annotations.rest.HttpErrorHandler;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Artsem Semianenka
 * Website: http://art4ul.com
 * 12/1/14.
 */
public class AnnotationProcessorTests {

    @Test
    public void loadAnnotationHandlerTest() {
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationBeforeHandler(BaseUrl.class) instanceof BaseUrlAnnotationBeforeHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationBeforeHandler(HttpErrorHandler.class) instanceof HttpErrorHandlerAnnotationBeforeHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationBeforeHandler(PathVariable.class) instanceof PathVariableAnnotationBeforeHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationBeforeHandler(RequestBody.class) instanceof RequestBodyAnnotationBeforeHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationBeforeHandler(RequestHeader.class) instanceof RequestHeaderAnnotationBeforeHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationBeforeHandler(RequestMapping.class) instanceof RequestMappingAnnotationBeforeHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationBeforeHandler(RequestParam.class) instanceof RequestParamAnnotationBeforeHandler);
    }
}

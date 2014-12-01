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

import com.art4ul.jcoon.annotations.BaseUrl;
import com.art4ul.jcoon.annotations.HttpErrorHandler;
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
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationHandler(BaseUrl.class) instanceof BaseUrlAnnotationHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationHandler(HttpErrorHandler.class) instanceof HttpErrorHandlerAnnotationHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationHandler(PathVariable.class) instanceof PathVariableAnnotationHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationHandler(RequestBody.class) instanceof RequestBodyAnnotationHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationHandler(RequestHeader.class) instanceof RequestHeaderAnnotationHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationHandler(RequestMapping.class) instanceof RequestMappingAnnotationHandler);
        Assert.assertTrue(AnnotationProcessor.getInstance().getAnnotationHandler(RequestParam.class) instanceof RequestParamAnnotationHandler);
    }
}

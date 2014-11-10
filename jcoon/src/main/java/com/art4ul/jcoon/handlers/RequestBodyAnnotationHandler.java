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

import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;

@ProcessAnnotation(RequestBody.class)
public class RequestBodyAnnotationHandler implements ParamAnnotationHandler {

    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        context.setBody(paramValue);
    }
}

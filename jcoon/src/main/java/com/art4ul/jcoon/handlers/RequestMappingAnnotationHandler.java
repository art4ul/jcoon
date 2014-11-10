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
import com.art4ul.jcoon.util.ArrayUtil;
import com.art4ul.jcoon.util.HttpRequestUtil;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;

@ProcessAnnotation(RequestMapping.class)
class RequestMappingAnnotationHandler implements ParamAnnotationHandler {
    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        RequestMapping requestMapping = (RequestMapping) annotation;
        if (requestMapping != null) {
            RequestMethod requestMethod = ArrayUtil.getFirstValue(requestMapping.method());
            if (requestMethod != null) {
                context.setHttpMethod(HttpMethod.valueOf(requestMethod.name()));
            }
            context.addUrlPath(ArrayUtil.getFirstValue(requestMapping.value()));
            HttpRequestUtil.addHeaders(context, requestMapping.headers());
        }

    }
}

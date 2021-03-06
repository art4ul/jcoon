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

import com.art4ul.jcoon.annotations.infrastructure.After;
import com.art4ul.jcoon.annotations.infrastructure.ProcessAnnotation;
import com.art4ul.jcoon.annotations.rest.Cookies;
import com.art4ul.jcoon.context.Context;
import com.art4ul.jcoon.models.Wrapper;
import com.google.common.base.Joiner;
import org.springframework.http.HttpHeaders;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by Artsem Semianenka
 * Website: http://art4ul.com
 * 12/20/14.
 */

@ProcessAnnotation(Cookies.class)
@After
public class CookiesAnnotationAfterHandler implements ParamAnnotationHandler {

    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        if (paramValue != null && paramValue instanceof Wrapper) {
            Wrapper wrapper = ((Wrapper) paramValue);
            Object wrappedParam = wrapper.getValue();
            HttpHeaders httpHeaders = context.getResponseEntity().getHeaders();
            List<String> cookies = httpHeaders.get("Set-Cookie");
            if (cookies != null) {
                if (wrappedParam instanceof String) {
                    String resultString = Joiner.on(";").join(cookies);
                    wrapper.setValue(resultString);
                } else if (wrappedParam instanceof List) {
                    wrapper.setValue(cookies);
                }
            }
        }
    }

}

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

package com.art4ul.jcoon.bean;

import com.art4ul.jcoon.annotations.BaseUrl;
import com.art4ul.jcoon.context.Context;
import com.art4ul.jcoon.context.RestClientContext;
import com.art4ul.jcoon.exception.RestClientCommonException;
import com.art4ul.jcoon.handlers.AnnotationProcessor;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;

class RestClientInterfaceInvocationHandler implements InvocationHandler {

    private Class<?> originalClass;
    private String baseUrl;
    private final RestTemplate restTemplate;

    public RestClientInterfaceInvocationHandler(Class<?> originalClass, String baseUrl, RestTemplate restTemplate) {
        this.originalClass = originalClass;
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] params) throws Throwable {
        Context context = new RestClientContext(object, method, params, restTemplate);

        if (method.isAnnotationPresent(BaseUrl.class)) {
            baseUrl = retrieveBaseUrl(context);
            return null;
        }
        context.setBaseUrl(baseUrl);

        // Process method annotations
        AnnotationProcessor.processAnnotations(context, originalClass.getAnnotations());
        // Process method annotations
        AnnotationProcessor.processAnnotations(context, method.getAnnotations());

        // Process method params
        for (int i = 0; i < params.length; i++) {
            Annotation[] annotations = method.getParameterAnnotations()[i];
            Object paramValue = params[i];
            AnnotationProcessor.processAnnotations(context, annotations, paramValue);
        }

        URI uri = context.buildUri();

        System.out.println("URI=" + uri.toString());

        HttpEntity httpEntity = context.createHttpEntity();


        ResponseEntity responseEntity = context.getRestTemplate().exchange(uri, context.getHttpMethod(), httpEntity,
                method.getReturnType());

        context.setResponseEntity(responseEntity);

        System.out.println("responseEntity=" + responseEntity.getBody());
        return responseEntity.getBody();
    }

    private String retrieveBaseUrl(Context context) {
        if (context.getParams().length != 1) {
            throw new RestClientCommonException("@BaseUrl method should have only one argument.");
        }
        Class<?> argumentClass = context.getMethod().getParameterTypes()[0];
        if (!argumentClass.equals(String.class)) {
            throw new RestClientCommonException("@BaseUrl method argument should be String type only.");
        }
        return context.getParams()[0].toString();
    }

}

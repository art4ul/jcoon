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

package com.art4ul.jcoon.context;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.net.URI;

public interface Context {

    public Object getObject();

    public Method getMethod();

    public Object[] getParams();

    public HttpHeaders getHttpHeaders();

    public Context addHttpParam(String key, Object value);

    public URI buildUri();

    public Context setBaseUrl(String baseUrl);

    public Context setUrlPath(String urlPath);

    public Context addUrlPath(String methodUrlPath);

    public Context setHttpMethod(HttpMethod httpMethod);

    public HttpMethod getHttpMethod();

    public Context addUriVariable(String key, Object value);

    public ResponseEntity getResponseEntity();

    public Context setResponseEntity(ResponseEntity responseEntity);

    public HttpEntity createHttpEntity();

    public Context setBody(Object body);

    public RestTemplate getRestTemplate();


}

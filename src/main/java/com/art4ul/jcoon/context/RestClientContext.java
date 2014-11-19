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

import com.art4ul.jcoon.exception.InitializationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RestClientContext implements Context {


    private Object object;
    private Method method;
    private Object[] inputParams;
    private Map<String, Object> httpParams;


    private String baseUrl;
    private String urlPath = "";
    private HttpMethod httpMethod = HttpMethod.GET;

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private HttpEntity httpEntity;
    private Object body;
    private final Map<String, Object> uriVariable = new HashMap<String, Object>();
    private ResponseEntity responseEntity;


    public RestClientContext(Object object, Method method, Object[] params, RestTemplate restTemplate) {
        this.object = object;
        this.method = method;
        this.inputParams = params;
        this.restTemplate = restTemplate;
    }

    @Override
    public Object getObject() {
        return object;
    }


    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getParams() {
        return inputParams;
    }

    @Override
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    @Override
    public Context setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public Context setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    @Override
    public Context addUrlPath(String urlPath) {
        if (urlPath != null) {
            if (!urlPath.startsWith("/")) {
                urlPath = "/" + urlPath;
            }
            this.urlPath += urlPath;
        }

        return this;
    }


    @Override
    public Context addHttpParam(String key, Object value) {
        if (httpParams == null) {
            httpParams = new HashMap<String, Object>();
        }
        httpParams.put(key, value);
        return this;
    }

    @Override
    public Context addUriVariable(String key, Object value) {
        uriVariable.put(key, value);
        return this;
    }

    @Override
    public Context setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    @Override
    public URI buildUri() {
        if (baseUrl == null) {
            throw new InitializationException("@BaseUrl is not set.");
        }
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromUriString(baseUrl + urlPath);

        if (httpParams != null) {
            //if (httpMethod == HttpMethod.GET) {
            for (String key : httpParams.keySet()) {
                componentsBuilder.queryParam(key, httpParams.get(key));
            }
            //}
        }
        return componentsBuilder.buildAndExpand(uriVariable).toUri();
    }

    public ResponseEntity getResponseEntity() {
        return responseEntity;
    }

    public Context setResponseEntity(ResponseEntity responseEntity) {
        this.responseEntity = responseEntity;
        return this;
    }

    public HttpEntity createHttpEntity() {
        return this.httpEntity = new HttpEntity(body, getHttpHeaders());
    }

    public Context setBody(Object body) {
        this.body = body;
        return this;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}

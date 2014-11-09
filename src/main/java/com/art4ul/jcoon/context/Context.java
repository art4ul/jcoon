package com.art4ul.jcoon.context;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.net.URI;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/29/14.
 */
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

    public Context addUriVarible(String key, Object value);

    public ResponseEntity getResponseEntity();

    public Context setResponseEntity(ResponseEntity responseEntity);

    public HttpEntity createHttpEntity();

    public Context setBody(Object body);

    public RestTemplate getRestTemplate();


}

package com.art4ul.jcoon.bean;

import com.art4ul.jcoon.annotations.BaseUrl;
import com.art4ul.jcoon.context.Context;
import com.art4ul.jcoon.context.RestClientContext;
import com.art4ul.jcoon.handlers.AnnotationProcessor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/23/14.
 */
public class RestClientInterfaceInvocationHandler implements InvocationHandler {

    private Class<?> orignalClass;

    private String baseUrl;


    private RestTemplate restTemplate;


    public RestClientInterfaceInvocationHandler(Class<?> orignalClass, String baseUrl, RestTemplate restTemplate) {
        this.orignalClass = orignalClass;
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] params) throws Throwable {
        Context context = new RestClientContext(object, method, params, restTemplate);

        if (method.isAnnotationPresent(BaseUrl.class)) {
            updateBaseUrl(context);
            return null;
        }
        context.setBaseUrl(baseUrl);

        // Process method annotations
        for (Annotation classAnnotation : orignalClass.getAnnotations()) {
            AnnotationProcessor.process(context, classAnnotation);
        }

        // Process method annotations
        for (Annotation methodAnnotation : method.getAnnotations()) {
            AnnotationProcessor.process(context, methodAnnotation);
        }

        // Process method params
        for (int i = 0; i < params.length; i++) {
            Annotation[] annotations = method.getParameterAnnotations()[i];
            Object paramValue = params[i];
            for (Annotation annotation : annotations) {
                AnnotationProcessor.process(context, annotation, paramValue);
            }
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

    private void updateBaseUrl(Context context) {
        if (context.getParams().length != 1) {
            throw new RuntimeException("@BaseUrl method should have only one argument.");
        }
        Class<?> argumentClass = context.getMethod().getParameterTypes()[0];
        if (!argumentClass.equals(String.class)) {
            throw new RuntimeException("@BaseUrl method argument should be String type only.");
        }
        baseUrl = context.getParams()[0].toString();
    }

}

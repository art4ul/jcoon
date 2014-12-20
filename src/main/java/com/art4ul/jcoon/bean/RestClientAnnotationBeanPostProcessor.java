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

import com.art4ul.jcoon.annotations.rest.RestClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;

public class RestClientAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final RestTemplate restTemplate;

    public RestClientAnnotationBeanPostProcessor() {
        this.restTemplate = new RestTemplate();
    }

    public RestClientAnnotationBeanPostProcessor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestClientAnnotationBeanPostProcessor(List<HttpMessageConverter<?>> messageConverters) {
        this.restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            RestClient restClient = field.getAnnotation(RestClient.class);
            if (restClient != null) {
                field.setAccessible(true);
                Object proxyInstance = createProxyInstance(bean.getClass().getClassLoader(), restClient.value(), field.getType()); //  TODO: Optimization , do not create new instance for same classes
                ReflectionUtils.setField(field, bean, proxyInstance);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private Object createProxyInstance(ClassLoader classLoader, String baseUrl, Class<?>... classType) {
        return Proxy.newProxyInstance(classLoader, classType, new RestClientInterfaceInvocationHandler(classType[0], baseUrl, restTemplate));
    }


}

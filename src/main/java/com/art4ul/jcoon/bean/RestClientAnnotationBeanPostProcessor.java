package com.art4ul.jcoon.bean;

import com.art4ul.jcoon.annotations.RestClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/15/14.
 */

public class RestClientAnnotationBeanPostProcessor implements BeanPostProcessor {

    private RestTemplate restTemplate;

    public RestClientAnnotationBeanPostProcessor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

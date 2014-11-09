package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import com.art4ul.jcoon.util.ArrayUtil;
import com.art4ul.jcoon.util.HttpRequestUtil;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 11/9/14.
 */

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

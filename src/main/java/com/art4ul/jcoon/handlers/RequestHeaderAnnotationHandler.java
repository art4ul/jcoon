package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.Annotation;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/30/14.
 */

@ProcessAnnotation(RequestHeader.class)
class RequestHeaderAnnotationHandler implements ParamAnnotationHandler {
    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        RequestHeader requestHeader = (RequestHeader) annotation;
        if (requestHeader.value() != null && !requestHeader.value().isEmpty()) {
            context.getHttpHeaders().add(requestHeader.value(), paramValue.toString());
        }
    }
}

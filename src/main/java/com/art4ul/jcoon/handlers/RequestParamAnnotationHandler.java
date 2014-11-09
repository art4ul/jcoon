package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/30/14.
 */
@ProcessAnnotation(RequestParam.class)
class RequestParamAnnotationHandler implements ParamAnnotationHandler {

    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        RequestParam requestParam = (RequestParam) annotation;
        if (requestParam.value() != null && !requestParam.value().isEmpty()) {
            context.addHttpParam(requestParam.value(), paramValue);
        }

    }
}

package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.BaseUrl;
import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;

import java.lang.annotation.Annotation;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/30/14.
 */

@ProcessAnnotation(BaseUrl.class)
class BaseUrlAnnotationHandler implements ParamAnnotationHandler {

    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        context.setBaseUrl(paramValue.toString());
    }
}

package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.context.Context;

import java.lang.annotation.Annotation;

/**
 * Created by Artsem Semianenka
 * Website: http://art4ul.com
 * 10/30/14.
 */
interface ParamAnnotationHandler {

    void doHandle(Context context, Annotation annotation, Object paramValue);
}

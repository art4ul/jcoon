package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.annotation.Annotation;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/30/14.
 */
@ProcessAnnotation(PathVariable.class)
class PathVariableAnnotationHandler implements ParamAnnotationHandler {
    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        PathVariable pathVariable = (PathVariable) annotation;
        if (pathVariable.value() != null && !pathVariable.value().isEmpty()) {
            context.addUriVarible(pathVariable.value(), paramValue.toString());
        }
    }
}

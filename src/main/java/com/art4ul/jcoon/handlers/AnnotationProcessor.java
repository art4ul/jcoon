package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 11/9/14.
 */
public class AnnotationProcessor {

    private static final Map<Class<?>, ParamAnnotationHandler> PARAM_ANNOTATION_HANDLER_MAP = new HashMap<Class<?>, ParamAnnotationHandler>();

    static {
        Reflections reflections = new Reflections(AnnotationProcessor.class.getPackage().getName());
        Set<Class<? extends ParamAnnotationHandler>> classes = reflections.getSubTypesOf(ParamAnnotationHandler.class);
        for (Class<? extends ParamAnnotationHandler> handlerClass : classes) {
            ProcessAnnotation annotationHandler = handlerClass.getAnnotation(ProcessAnnotation.class);
            if (annotationHandler != null) {
                try {
                    PARAM_ANNOTATION_HANDLER_MAP.put(annotationHandler.value(), handlerClass.newInstance());
                } catch (Exception e) {
                    throw new RuntimeException("Exception during creating new instance of ParamAnnotationHandler");
                }
            }
        }
    }

    public static void process(Context context, Annotation annotation) {
        process(context, annotation, null);
    }

    public static void process(Context context, Annotation annotation, Object paramValue) {
        ParamAnnotationHandler annotationHandler = PARAM_ANNOTATION_HANDLER_MAP.get(annotation.annotationType());
        if (annotationHandler != null) {
            annotationHandler.doHandle(context, annotation, paramValue);
        }
    }
}

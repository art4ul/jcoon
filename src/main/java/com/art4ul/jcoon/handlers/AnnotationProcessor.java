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

package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import com.art4ul.jcoon.exception.InitializationException;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
                    throw new InitializationException("Exception during creating new instance of ParamAnnotationHandler");
                }
            }
        }
    }

    private static void process(Context context, Annotation annotation, Object paramValue) {
        ParamAnnotationHandler annotationHandler = PARAM_ANNOTATION_HANDLER_MAP.get(annotation.annotationType());
        if (annotationHandler != null) {
            annotationHandler.doHandle(context, annotation, paramValue);
        }
    }

    public static void processAnnotations(Context context, Annotation[] annotations) {
        processAnnotations(context, annotations, null);
    }

    public static void processAnnotations(Context context, Annotation[] annotations, Object paramValue) {
        for (Annotation annotation : annotations) {
            AnnotationProcessor.process(context, annotation, paramValue);
        }
    }
}

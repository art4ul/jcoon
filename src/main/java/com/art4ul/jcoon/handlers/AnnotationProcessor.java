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
import com.google.common.reflect.ClassPath;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationProcessor {

    private static class AnnotationProcessorHelper {
        public static final AnnotationProcessor INSTANCE = new AnnotationProcessor();
    }

    private final Map<Class<?>, ParamAnnotationHandler> paramAnnotationHandlerMap = new HashMap<Class<?>, ParamAnnotationHandler>();


    private AnnotationProcessor() {
        try {
            ClassPath classPath = ClassPath.from(AnnotationProcessor.class.getClassLoader());
            Set<ClassPath.ClassInfo> classInfo = classPath.getTopLevelClasses(AnnotationProcessor.class.getPackage().getName());
            for (ClassPath.ClassInfo info : classInfo) {
                Class<?> clazz = info.load();
                if (ParamAnnotationHandler.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                    Class<? extends ParamAnnotationHandler> handlerClass = (Class<? extends ParamAnnotationHandler>) clazz;
                    ProcessAnnotation annotationHandler = handlerClass.getAnnotation(ProcessAnnotation.class);
                    if (annotationHandler != null) {
                        paramAnnotationHandlerMap.put(annotationHandler.value(), handlerClass.newInstance());
                    }
                }
            }
        } catch (Exception e) {
            throw new InitializationException("Exception during creating new instance of ParamAnnotationHandler");
        }
    }

    public static AnnotationProcessor getInstance() {
        return AnnotationProcessorHelper.INSTANCE;
    }


    private void process(Context context, Annotation annotation, Object paramValue) {
        ParamAnnotationHandler annotationHandler = getAnnotationHandler(annotation.annotationType());
        if (annotationHandler != null) {
            annotationHandler.doHandle(context, annotation, paramValue);
        }
    }

    public void processAnnotations(Context context, Annotation[] annotations) {
        processAnnotations(context, annotations, null);
    }

    public void processAnnotations(Context context, Annotation[] annotations, Object paramValue) {
        for (Annotation annotation : annotations) {
            process(context, annotation, paramValue);
        }
    }

    protected ParamAnnotationHandler getAnnotationHandler(Class<?> clazz) {
        return paramAnnotationHandlerMap.get(clazz);
    }
}

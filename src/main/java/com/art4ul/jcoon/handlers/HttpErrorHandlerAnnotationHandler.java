package com.art4ul.jcoon.handlers;

import com.art4ul.jcoon.annotations.HttpErrorHandler;
import com.art4ul.jcoon.annotations.ProcessAnnotation;
import com.art4ul.jcoon.context.Context;
import org.springframework.web.client.ResponseErrorHandler;

import java.lang.annotation.Annotation;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 11/9/14.
 */
@ProcessAnnotation(HttpErrorHandler.class)
public class HttpErrorHandlerAnnotationHandler implements ParamAnnotationHandler {
    @Override
    public void doHandle(Context context, Annotation annotation, Object paramValue) {
        if (paramValue instanceof ResponseErrorHandler) {
            context.getRestTemplate().setErrorHandler((ResponseErrorHandler) paramValue);
        } else {
            System.out.println("Warning: Annotated @HttpErrorHandler input parameter must be inherited from ResponseErrorHandler");
        }
    }
}

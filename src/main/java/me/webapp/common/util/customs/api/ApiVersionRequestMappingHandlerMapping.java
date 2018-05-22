package me.webapp.common.util.customs.api;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {


    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }

    private RequestCondition<ApiVersionRequestCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionRequestCondition(apiVersion.value());
    }
}

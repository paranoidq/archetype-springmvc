package me.webapp.support.customPropertyEditor;

import java.lang.annotation.*;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EditorTargetType {

    Class<?> value();
}

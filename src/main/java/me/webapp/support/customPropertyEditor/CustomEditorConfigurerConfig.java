package me.webapp.support.customPropertyEditor;

import me.webapp.common.util.reflection.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class CustomEditorConfigurerConfig {

    private static Logger logger = LoggerFactory.getLogger(CustomEditorConfigurerConfig.class);

    private static Map<Class<?>, Class<? extends PropertyEditor>> customEditors = null;
    static {
        customEditors = new HashMap<>();

        try {
            List<Class<?>> customEditorClasses = ReflectionUtil.getClassesBySuperClass("me.webapp.support.customPropertyEditor", PropertyEditorSupport.class);

            // 注册customEditors
            for (Class<?> clazz : customEditorClasses) {
                EditorTargetType annotation = clazz.getAnnotation(EditorTargetType.class);
                if (annotation != null) {
                    customEditors.put(
                        annotation.value(), (Class<? extends PropertyEditor>) clazz);
                }
            }
        } catch (IOException e) {
            logger.error("反射加载自定义PropertyEditor失败", e);
        }
    }

    /**
     * 注册CustomEditorConfigurer，从而添加自定义的PropertyEditor进行属性的格式解析
     *
     * @return
     */
    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setCustomEditors(customEditors);
        return customEditorConfigurer;
    }

}

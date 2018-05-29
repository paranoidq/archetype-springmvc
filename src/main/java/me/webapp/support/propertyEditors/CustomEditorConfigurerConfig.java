package me.webapp.support.propertyEditors;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.beans.PropertyEditor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class CustomEditorConfigurerConfig {
    private static Map<Class<?>, Class<? extends PropertyEditor>> customEditors = null;
    static {
        customEditors = new HashMap<>();

        // 注册customEditors
        customEditors.put(Date.class, DatePropertyEditor.class);  // date
    }

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setCustomEditors(customEditors);
        return customEditorConfigurer;
    }
}

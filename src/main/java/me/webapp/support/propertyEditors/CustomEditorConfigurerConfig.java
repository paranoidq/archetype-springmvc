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
        customEditors.put(Date.class, DatePropertyEditor.class);
        customEditors.put(Map.class, KvPropertyEditor.class);
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

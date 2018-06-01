package me.webapp.support.customPropertyPlaceholder;

import me.webapp.common.util.reflection.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class CustomPropertyPlaceholderConfig {
    private static final Logger logger = LoggerFactory.getLogger(CustomPropertyPlaceholderConfig.class);

    @Bean
    public PropertyPlaceholderConfigurer customPropertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new CustomPropertyPlaceholderConfigurer();

        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resourceResolver.getResources("classpath:config/*.properties");
        } catch (IOException e) {
            logger.error("无法加载配置文件资源", e);
        }

        configurer.setLocations(resources);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_FALLBACK);
        configurer.setFileEncoding("UTF-8");
        return configurer;
    }


    /**
     * 定制化的属性占位符configurer
     *
     * 如果属性没有匹配的AbstractCustomPropertyPlaceholderProcessor实例，则保留配置文件中的原值
     * 如果匹配到了，则调用{@link AbstractCustomPropertyPlaceholderProcessor#process(String, String)}对属性值进行处理，并返回
     *
     * 多个AbstractCustomPropertyPlaceholderProcessor实例匹配时，会进行级联处理，前一个处理器返回的propValue会作为后一个处理器的输入
     * 最后返回的是经过所有匹配的处理器级联处理过的属性值
     *
     *
     */
    public static class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

        private static final Logger logger = LoggerFactory.getLogger(CustomPropertyPlaceholderConfigurer.class);

        private static List<AbstractCustomPropertyPlaceholderProcessor> customPropertyPlaceholderProcessors;

        /**
         * 构造函数
         *
         * 通过反射注册所有的AbstractCustomPropertyPlaceholderProcessor实例
         */
        public CustomPropertyPlaceholderConfigurer() {
            customPropertyPlaceholderProcessors = new LinkedList<>();

            // 这个过程在工厂后处理器阶段，无法通过注解扫描的方式自动注入？？
            try {
                List<Class<?>> classes = ReflectionUtil.getClassesBySuperClass("me.webapp.support.customPropertyPlaceholder", AbstractCustomPropertyPlaceholderProcessor.class);
                for (Class<?> clazz : classes) {
                    customPropertyPlaceholderProcessors.add((AbstractCustomPropertyPlaceholderProcessor) clazz.newInstance());
                }
            } catch (IOException e) {
                logger.error("无法加载AbstractCustomPropertyPlaceholderProcessor实现类", e);
            } catch (InstantiationException e) {
                logger.error("无法构造AbstractCustomPropertyPlaceholderProcessor实现类的实例", e);
            } catch (IllegalAccessException e) {
                logger.error("无法构造AbstractCustomPropertyPlaceholderProcessor实现类的实例", e);
            }

        }

        /**
         * 通过依次检查所有的AbstractCustomPropertyPlaceholderProcessor实例来进行属性占位符的处理
         *
         * @param propertyName
         * @param propertyValue
         * @return
         */
        @Override
        protected String convertProperty(String propertyName, String propertyValue) {
            String lastPropValue = propertyValue;
            for (AbstractCustomPropertyPlaceholderProcessor propertyPlaceholderProcessor
                    : customPropertyPlaceholderProcessors) {
                lastPropValue = propertyPlaceholderProcessor.process(propertyName, lastPropValue);
            }
            return propertyValue;
        }

    }
}

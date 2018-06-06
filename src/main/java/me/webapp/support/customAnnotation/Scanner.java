package me.webapp.support.customAnnotation;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class Scanner extends ClassPathBeanDefinitionScanner {
    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 注册待扫描的自定注解类
     */
    @Override
    protected void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(BeProxy.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 定制扫描自定义注解后的处理业务逻辑
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitionHolders) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            // 在bean的定义的基础上额外统一增加属性：innerClassName
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            // 强制修改了bean在定义时的class属性，这里修改为FactoryBean是为了实现更复杂的初始化逻辑（即创建代理
            definition.setBeanClass(ProxyFactoryBean.class);
        }
        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition)
            && beanDefinition.getMetadata().hasAnnotation(BeProxy.class.getName());
    }
}


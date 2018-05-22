package me.webapp.sample.hooks.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class ApplicationEventPublisherAwareHook implements ApplicationEventPublisherAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationEventPublisherAwareHook.class);

    private ApplicationEventPublisher publisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
        // 保存ApplicationContextPublisher对象
        // TODO: 利用ApplicationEventPublishser发布事件
    }
}

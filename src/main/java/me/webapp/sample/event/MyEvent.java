package me.webapp.sample.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class MyEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }

}

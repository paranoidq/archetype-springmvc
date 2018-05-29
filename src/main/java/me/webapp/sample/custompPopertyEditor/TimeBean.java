package me.webapp.sample.custompPopertyEditor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class TimeBean {

    @Value("2018-5-29 15:26:34")
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

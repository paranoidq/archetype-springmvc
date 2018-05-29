package me.webapp.sample.custompPopertyEditor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class TestBean {

    @Value("2018-5-29 15:26:34")
    private Date time;

    @Value("a=b&c=d")
    private Map<String, String> params;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}

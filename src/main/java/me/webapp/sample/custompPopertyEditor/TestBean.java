package me.webapp.sample.custompPopertyEditor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;
import java.util.Map;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class TestBean {

    @Value("2018-5-29")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

package me.webapp.support.propertyEditors;

import me.webapp.sample.custompPopertyEditor.TimeBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:config/applicationContext-all.xml", "classpath:config/spring-dispatcher-servlet.xml" })
public class CustomEditorConfigurerConfigTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Test
    public void testDateEditorConfigurer() throws Exception {
        TimeBean timeBean = (TimeBean) wac.getBean("timeBean");
        System.out.println(timeBean.getTime());
    }
}
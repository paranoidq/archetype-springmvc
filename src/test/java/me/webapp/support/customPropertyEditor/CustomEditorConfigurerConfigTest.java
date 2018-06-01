package me.webapp.support.customPropertyEditor;

import me.webapp.sample.custompPopertyEditor.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * {@link SpringJUnit4ClassRunner}用于在Junit环境下提供Spring TestContext Framework的功能
 * {@link ContextConfiguration}用于加载配置文件，可以通过locations加载xml配置文件或通过classes加载配置类
 * {@link ActiveProfiles}用于激活profile
 * {@link WebAppConfiguration}用于表示创建WebApplicationContext用于测试环境，默认为WEB目录为src/main/webapp
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:config/applicationContext-all.xml", "classpath:config/spring-dispatcher-servlet.xml" })
public class CustomEditorConfigurerConfigTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Test
    public void testDateEditorConfigurer() throws Exception {
        TestBean timeBean = (TestBean) wac.getBean("testBean");
        System.out.println(timeBean.getTime());
        System.out.println(timeBean.getParams());
    }
}
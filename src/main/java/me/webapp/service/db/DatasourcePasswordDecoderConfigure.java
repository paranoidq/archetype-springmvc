package me.webapp.service.db;

import me.webapp.common.constants.AppConstants;
import me.webapp.common.util.encoding.EncodingUtil;
import me.webapp.common.util.security.DesUtil;
import me.webapp.exception.InitializeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.security.Key;
import java.util.Arrays;
import java.util.Properties;

/**
 * 加载jdbc.properties时自动解密password
 * 继承{@link PlaceholderConfigurerSupport}，并在{@link #processProperties(ConfigurableListableBeanFactory, Properties)}中实现解码逻辑
 *
 * 注意：该PropertyPlaceholderConfigurer只负责解析jdbc.properties，
 * 因此在xml中配置需要添加ignoreUnresolvablePlaceholders保证其他Configurer实例可以继续解析
 *
 * TODO：策略模式实现多种加解密算法
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class DatasourcePasswordDecoderConfigure extends PropertyPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(DatasourcePasswordDecoderConfigure.class);

    @Override
    protected void processProperties(ConfigurableListableBeanFactory configurableListableBeanFactory, Properties properties) throws BeansException {
        String encryptedPassword = (String) properties.get(AppConstants.JDBC_DATASOURCE_PASSWORD_KEY);
        String rawPassword = decPassword(encryptedPassword);
        logger.debug("成功解密JDBC密码, {}", rawPassword);
        properties.setProperty(AppConstants.JDBC_DATASOURCE_PASSWORD_KEY, rawPassword);

        // 坑: 必须调用父类函数，解析其余属性，否则不会填充，造成报错
        super.processProperties(configurableListableBeanFactory, properties);

        logger.debug(Arrays.toString(properties.entrySet().toArray()));
    }


    /**
     * 加密
     * @param rawPassword
     * @return
     */
    private static String encPassword(String rawPassword) {
        try {
            Key key = DesUtil.loadKey(AppConstants.JDBC_DATASOURCE_PASSWORD_ENC_DES_KEY.getBytes());
            byte[] encrypted = DesUtil.encrypt(rawPassword.getBytes(), key);
            return EncodingUtil.byteArray2Hex(encrypted);
        } catch (Exception e) {
            throw new InitializeException("Encrypt JDBC password error: DES", e);
        }
    }


    /**
     * 解密
     * @param encryptedPassword
     * @return
     */
    private static String decPassword(String encryptedPassword) {
        try {
            Key key = DesUtil.loadKey(AppConstants.JDBC_DATASOURCE_PASSWORD_ENC_DES_KEY.getBytes());
            byte[] decrypted = DesUtil.decrypt(EncodingUtil.hex2ByteArray(encryptedPassword), key);
            return new String(decrypted);
        } catch (Exception e) {
            throw new InitializeException("Decrypted JDBC password error: DES", e);
        }
    }


    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        String enc = encPassword("88863650qw");
        System.out.println(enc);
        String dec = decPassword(enc);
        System.out.println(dec);
    }
}

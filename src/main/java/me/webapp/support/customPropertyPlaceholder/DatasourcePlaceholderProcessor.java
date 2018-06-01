package me.webapp.support.customPropertyPlaceholder;

import me.webapp.common.constants.AppConstants;
import me.webapp.common.util.encoding.EncodingUtil;
import me.webapp.common.util.security.DesUtil;
import me.webapp.exception.InitializeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;

/**
 * 实现数据库密码的加密和解密，避免明文存储在.properties配置文件中
 *
 * TODO：策略模式实现多种加解密算法
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class DatasourcePlaceholderProcessor extends AbstractCustomPropertyPlaceholderProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DatasourcePlaceholderProcessor.class);

    @Override
    protected boolean match(String propName) {
        return AppConstants.JDBC_DATASOURCE_PASSWORD_KEY.equals(propName);
    }

    @Override
    protected String doProcess(String propName, String propValue) {
        String rawPassword = decPassword(propValue);
        logger.debug("成功解密JDBC密码, {}", rawPassword);
        return rawPassword;
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

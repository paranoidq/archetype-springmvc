package me.webapp.common.util.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 工具类：提供DES加解密、秘钥生成、加载功能
 *
 * @author paranoidq
 * @since 0.1
 */
public class DesUtil {
    /**
     * ALGORITHM 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     *
     * <pre>
     * DES                  key size must be equal to 56
     * DESede(TripleDES)    key size must be equal to 112 or 168
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
     * RC2                  key size must be between 40 and 1024 bits
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
     * </pre>
     *
     * 在Key loadKey(byte[] key)方法中使用下述代码
     * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
     * <code>
     * DESKeySpec dks = new DESKeySpec(key);
     * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
     * SecretKey secretKey = keyFactory.generateSecret(dks);
     * </code>
     */


    public static final String ALGORITHM = "DES";

    /**
     * 将byte[]数组加载为des key
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static Key loadKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(desKeySpec);
    }


    /**
     * 解密
     * @param data
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(byte[] data, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     * @param data
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(byte[] data, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 随机生成一个des key, 指定seed
     * @param seed
     * @return des key
     * @throws NoSuchAlgorithmException
     */
    public static Key initKey(byte[] seed) throws NoSuchAlgorithmException {
        SecureRandom secureRandom;
        if (seed != null && seed.length > 0) {
            secureRandom = new SecureRandom(seed);
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(secureRandom);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /**
     * 随机生成一个des key, 不指定seed
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Key initKey() throws NoSuchAlgorithmException {
        return initKey(null);
    }


    public static void main(String[] args) throws Exception {
        Key key = initKey(null);
        String src = "des";
        byte[] encryptData = encrypt(src.getBytes(), key);
        System.out.println(Base64.encodeBase64String(encryptData));

        byte[] decryptData = decrypt(encryptData, key);
        System.out.println(new String(decryptData));
    }

}

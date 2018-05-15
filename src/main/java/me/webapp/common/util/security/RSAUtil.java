package me.webapp.common.util.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

/**
 * 工具类：提供RSA加解密、秘钥生成、秘钥加载功能
 *
 * @author paranoidq
 * @since 0.1
 */
public class RSAUtil {

    public static final String ALGORITHM = "RSA";
    public static final String KEY_ALGORITHM = "RSA";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 生成秘钥对
     * @param keySize
     * @return
     * @throws Exception
     */
    public static KeyPair genKeyPair(int keySize) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        generator.initialize(keySize);
        return generator.generateKeyPair();
    }

    /**
     * 生成秘钥对
     * @param keySize
     * @param random
     * @return
     * @throws Exception
     */
    public static KeyPair genKeyPair(int keySize, SecureRandom random) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        generator.initialize(keySize, random);
        return generator.generateKeyPair();
    }


    /**
     * 私钥解密
     * @param encryptedData
     * @param privateKey
     * @return
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, PrivateKey privateKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     * @param encryptedData
     * @param publicKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(encryptedData);
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encryptByPrivateKey(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }


    /**
     * 从byte数组加载私钥
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey loadPrivateKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(key);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    /**
     * 从byte数组加载公钥
     * @param key
     * @param keyAlgorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey loadPublicKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(key);
        return keyFactory.generatePublic(publicKeySpec);
    }

    /**
     * 从pfx证书文件中加载私钥
     * @param pfxInputstream
     * @param password
     * @return
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws UnrecoverableKeyException
     */
    public static PrivateKey loadPrivateKeyFromPfx(InputStream pfxInputstream, String password) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(pfxInputstream, password.toCharArray());
        Enumeration<String> keyAliases = keyStore.aliases();
        if (keyAliases.hasMoreElements()) {
            String keyAlias = keyAliases.nextElement();
            return (PrivateKey) keyStore.getKey(keyAlias, password.toCharArray());
        } else {
            return null;
        }
    }

    /**
     * 从cer证书文件中加载公钥
     * @param cerFileStream
     * @return
     * @throws CertificateException
     */
    public static PublicKey loadPublicKeyFromCer(InputStream cerFileStream) throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(cerFileStream);
        return cert.getPublicKey();
    }

    /**
     * 从der文件中加载私钥
     * @param encodedKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey loadPrivateKeyInDerFormat(byte[] encodedKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 从der文件中加载公钥
     * @param encodedKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey loadPublicKeyInPemFormat(byte[] encodedKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new X509EncodedKeySpec(encodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从pem格式的文件中加载私钥
     * @param inputStream
     * @return
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static PrivateKey loadPrivateKeyInPemFormat(InputStream inputStream) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PemReader pemReader = new PemReader(bufferedReader);
        PemObject pemObject = pemReader.readPemObject();
        return loadPrivateKey(pemObject.getContent());
    }

    /**
     * 从pem格式的文件中加载公钥
     * @param inputStream
     * @return
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static PublicKey loadPublicKeyInPemFormat(InputStream inputStream) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PemReader pemReader = new PemReader(bufferedReader);
        PemObject pemObject = pemReader.readPemObject();
        return loadPublicKey(pemObject.getContent());
    }


    public static void savePrivateKeyInPemFormat(PrivateKey privateKey, String absFilePath) throws IOException {
        PemWriter writer = new PemWriter(new OutputStreamWriter(new FileOutputStream(absFilePath)));
        PemObject object = new PemObject("PRIVATE KEY", privateKey.getEncoded());
        writer.writeObject(object);
        writer.close();
    }

    public static void savePublicKeyInPemFormat(PublicKey publicKey, String absFilePath) throws IOException {
        PemWriter writer = new PemWriter(new OutputStreamWriter(new FileOutputStream(absFilePath)));
        PemObject object = new PemObject("PUBLIC KEY", publicKey.getEncoded());
        writer.writeObject(object);
        writer.close();
    }

    public static void savePrivateKeyInDerFormat() {

    }

    public static void savePublicKeyInDerFormat() throws NoSuchAlgorithmException {

    }




    public static void main(String[] args) {

    }


}

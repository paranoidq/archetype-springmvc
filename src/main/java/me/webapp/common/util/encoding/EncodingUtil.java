package me.webapp.common.util.encoding;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * 编码工具类
 *
 * base64编解码，URLEncode编解码，hexToByteArray之间的转化
 *
 * @author paranoidq
 * @since 0.1
 */
public class EncodingUtil {


    /**
     * BASE64编码
     * @param data
     * @return
     */
    public static byte[] encodeBase64(byte[] data) {
        return Base64.encodeBase64(data);
    }

    /**
     * BASE64解码
     * @param data
     * @return
     */
    public static byte[] decodeBase64(byte[] data) {
        return Base64.decodeBase64(data);
    }

    /**
     * byte数组转16进制字符串
     * @param data
     * @return
     */
    public static String byteArray2Hex(byte[] data) {
        return String.valueOf(Hex.encodeHex(data));
    }


    /**
     * byte数组转16进制字符串，包含分隔符
     * @param data
     * @param sp
     * @return
     */
    public static String byteArray2Hex(byte[] data, char sp) {
        char[] chs = Hex.encodeHex(data);
        StringBuilder sb = new StringBuilder(3 * chs.length - 1);
        for (int i = 0; i < chs.length; i+=2) {
            sb.append(chs[i]).append(chs[i+1]).append(sp);
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转byte数组
     * @param data
     * @return
     * @throws DecoderException
     */
    public static byte[] hex2ByteArray(String data) throws DecoderException {
        return Hex.decodeHex(data.toCharArray());
    }

    /**
     * URLEncode
     * @param data
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String data, Charset encoding) throws UnsupportedEncodingException {
        return URLEncoder.encode(data, encoding.name());
    }

    /**
     * URLDecode
     * @param data
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlDecode(String data, Charset encoding) throws UnsupportedEncodingException {
        return URLDecoder.decode(data, encoding.name());
    }

}

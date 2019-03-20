package cn.team.block.assets.tools;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Base64加密方式
 */
public class Cipher {

    static final BASE64Encoder encoder = new BASE64Encoder();
    static final BASE64Decoder decoder = new BASE64Decoder();
    static final String FLAG = "Cipher:";

    /**
     * 解密
     *
     * @param cipher 加密内容
     * @return
     */
    public static String decode(String cipher) {
        if (StringUtils.isEmpty(cipher)) {
            return null;
        }
        try {
            String decipher = new String(decoder.decodeBuffer(cipher), "UTF-8");
            if (!StringUtils.startsWith(decipher, FLAG)) {
                return cipher;
            }
            return decipher.substring(FLAG.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(String decipher) {
        if (StringUtils.isEmpty(decipher)) {
            return null;
        }
        decipher = FLAG + decipher;
        final byte[] textByte;
        try {
            textByte = decipher.getBytes("UTF-8");
            return encoder.encode(textByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

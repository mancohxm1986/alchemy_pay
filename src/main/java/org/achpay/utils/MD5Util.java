package org.achpay.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

/**
 * Desc: MD5摘要算法
 * Author: songshibin
 * Create Date: 15/12/14
 */
public class MD5Util {

    public MD5Util() {

    }

    /**
     * 获取字符串的MD5值
     * @param s 待计算字符串
     * @param encoding 字符串编码
     * @return MD5值
     */
    public static String getMd5(String s, String encoding) {
        byte abyte0[];
        MessageDigest messagedigest;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
            throw new IllegalArgumentException("no md5 support");
        }
        try {
            messagedigest.update(s.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        abyte0 = messagedigest.digest();
        return byte2hex(abyte0);

    }

    /**
     * 获取字节数组的MD5值
     * @param bytes 字节数组
     * @return MD5值
     */
    public static String getMd5(byte[] bytes) {
        byte abyte0[];
        MessageDigest messagedigest;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
            throw new IllegalArgumentException("no md5 support");
        }
        messagedigest.update(bytes);
        abyte0 = messagedigest.digest();
        return byte2hex(abyte0);

    }

    /**
     * 转换直接数组为16进制字符串
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    public static String byte2hex(byte bytes[]) {
        StringBuffer stringBuffer = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Long.toString((long) bytes[i] & (long) 255, 16));
        }

        return stringBuffer.toString().toUpperCase();
    }

    public static String ascii(TreeMap<String, Object> map, String md5key) {
        StringBuffer result = new StringBuffer();
        if (null != map && map.size() > 0) {
            for (String key : map.keySet()) {
                if(map.get(key)!=null){
                    result.append(key + "=" + map.get(key) + "&");
                }
            }
            result.append("key=" + md5key);
        } else {
            return "";
        }
        return result.toString();
    }

    // 编码方式
    private static final String CONTENT_CHARSET = "UTF-8";

    private static String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
            "f" };

    public static String getMD5(String sInput) {
        try {
            return getMD5(sInput, CONTENT_CHARSET);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getMD5(String sInput, String charset) throws Exception {
        MessageDigest alga = MessageDigest.getInstance("MD5");
        alga.update(sInput.getBytes(charset));
        byte digesta[] = alga.digest();
        return byteArrayToHexString(digesta);
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            sb.append(byteToHexString(b[i]));

        return sb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}

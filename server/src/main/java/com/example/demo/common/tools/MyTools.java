package com.example.demo.common.tools;

import org.apache.commons.codec.binary.Base64;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * 常用的工具类
 * @author 杨小平
 * @date 2022年10月31日15:40:50
 */
public class MyTools {

    /**
     * base64 加密
     *
     * @param str 密码
     * @return 加密密码
     */
    public static String base64En(String str) {
        Base64 base64 = new Base64();
        byte[] encode = base64.encode(str.getBytes());
        return new String(encode);
    }

    /**
     * base64解密
     *
     * @param encodeStr
     * @return
     */
    @SuppressWarnings("static-access")
    public static String base64De(String encodeStr) {
        Base64 base64 = new Base64();
        byte[] decodeStr = base64.decodeBase64(encodeStr);
        return new String(decodeStr);
    }

    /**
     * 获取本机ip
     *
     * @return String
     * @throws UnknownHostException
     */
    public static String getLocalHostIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 判断String类型的数据是否为空 null,""," " 为true "A"为false
     *
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return (null == str || str.trim().length() == 0);
    }

    /**
     * 判断list类型的数据是否为空 null,[] 为 true
     *
     * @return boolean
     */
    public static boolean isEmpty(List<?> list) {
        return (null == list || list.size() == 0);
    }

    /**
     * 判断Map类型的数据是否为空 null,[] 为true
     *
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (null == map || map.size() == 0);
    }

}

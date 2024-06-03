package org.achpay.utils;

import java.util.Map;
import java.util.TreeMap;

public class SignUtils {
    public static String getSign(Map<String, Object> params, String privatekey) {

        MapRemoveNullUtil.removeNullEntry(params);//先移除一下空值

        Map<String, Object> map = new TreeMap<>(params);
        //以key1 = value1&key2 = value2拼接参数
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> s : map.entrySet()) {
            String key = s.getKey();
            Object value = s.getValue();
            //拼接
            stringBuilder.append(key).append("=").append(value).append("&");
        }
        //拼接商户秘钥
        stringBuilder.append("key=").append(privatekey);
        String sign = MD5Util.getMd5(stringBuilder.toString().getBytes()).toUpperCase();
        return sign;
    }

}

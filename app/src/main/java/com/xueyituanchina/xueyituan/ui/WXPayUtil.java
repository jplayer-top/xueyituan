package com.xueyituanchina.xueyituan.ui;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class WXPayUtil {


    public static String createSign(SortedMap<String, String> parameters,
                                    String appKey) throws Exception {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();
            if (val != null
                && !val.equals("")
                && !key.equals("sign")
                && !key.equals("key")) {
                sb.append(key + "=" + val + "&");
            }
        }

        sb.append("key=" + appKey);

        return  MD5(sb.toString());
    }

    /**

    /**
     * 生成 MD5
     *
     * @param data
     *            待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1,
                                                                           3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取当前时间戳，单位秒
     * 
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }


}

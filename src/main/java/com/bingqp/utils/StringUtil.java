package com.bingqp.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;

public class StringUtil {

    public static boolean isBlank(String str) {
        return org.springframework.util.StringUtils.isEmpty(str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String trim(String str) {
        return org.springframework.util.StringUtils.trimWhitespace(str);
    }

    public static Collection<String> split(String str, String splitor) {
        if (isBlank(str)) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(str.split(splitor));
    }

    public static Collection<String> split(String str) {
        if (isBlank(str)) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(str.split(","));
    }

    public static String keys(Object... keys) {
        return keys(":", keys);
    }

    public static String keys(String splitor, Object... keys) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            if (i > 0) {
                sb.append(splitor);
            }
            sb.append(keys[i].toString());
        }

        return sb.toString();
    }

    public static String backslashTranslate(String str) {

        if (StringUtil.isBlank(str)) {
            return str;
        }

        if ("\\t".equals(str)) {
            return "\t";
        }
        if (str.startsWith("\\u")) {
            char c = (char) Integer.parseInt(str.substring(2), 16);
            return Character.toString(c);
        }
        return str;
    }

    public static String escapeNullString(String str) {
        return str != null ? str : "";
    }

    public static boolean isJsonObject(String content) {
        try {
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

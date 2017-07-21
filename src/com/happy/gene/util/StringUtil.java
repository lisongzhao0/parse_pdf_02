package com.happy.gene.util;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class StringUtil {

    public static final StringUtil newInstance() { return new StringUtil(); }

    public StringUtil() {}

    public boolean isEmpty(String string) {
        return null==string || "".equals(string.trim());
    }
}

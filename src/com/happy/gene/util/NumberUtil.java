package com.happy.gene.util;

/**
 * Created by zhaolisong on 02/05/2017.
 */
public class NumberUtil {

    public static final String NUMBER_INT   = "[-]{0,1}[0-9]+";
    public static final String NUMBER_FLOAT = "[-]{0,1}[0-9]+[.]{0,1}[0-9]*";

    public static NumberUtil newInstance() {
        return new NumberUtil();
    }

    private NumberUtil() {}

    public boolean isInteger(String strInt) {
        return strInt instanceof String ? strInt.matches(NUMBER_INT) : false;
    }
    public boolean isFloat(String strFloat) {
        return strFloat instanceof String ? strFloat.matches(NUMBER_FLOAT) : false;
    }

    public Integer parseInteger(String strInt) {
        if (isInteger(strInt)) {
            return Integer.parseInt(strInt);
        }
        return null;
    }
    public Float parseFloat(String strFloat) {
        if (isFloat(strFloat)) {
            return Float.parseFloat(strFloat);
        }
        return null;
    }

}

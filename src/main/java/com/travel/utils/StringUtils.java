package com.travel.utils;

/**
 * @authr Summerday
 *
 * 字符串工具类
 */
public class StringUtils {
    private StringUtils(){

    }

    /**
     * 判断是否为空
     */
    public static boolean isEmpty(String str){
        return str == null||str.trim().length() == 0;
    }

    /**
     * 判断a，b是否相等
     * @param a
     * @param b
     * @return  都为null，或者相等
     */
    public static boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

    /**
     * 忽视大小写，比较大小
     * @param a
     * @param b
     * @return 都为null，或者忽视大小写情况下相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equalsIgnoreCase(b);
        }
    }

}

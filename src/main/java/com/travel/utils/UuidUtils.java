package com.travel.utils;

import java.util.UUID;

/**
 * @auther Summerdays
 *
 * 产生UUID随机字符串工具类
 */
public class UuidUtils {
    private UuidUtils(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(UuidUtils.getUuid());
        System.out.println(UuidUtils.getUuid());
        System.out.println(UuidUtils.getUuid());
        System.out.println(UuidUtils.getUuid());
    }
}

package com.ok.mall.utils;

import java.util.UUID;

/**
 * @ClassName UuidUtils
 * @Description 生成UUID的工具类
 * @Author Iverson
 * @Date 2018/6/16 2:08
 **/
public class UuidUtils {

    /**
     * 生成UUID
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }
}

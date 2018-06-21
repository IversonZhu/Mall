package com.ok.mall.utils;

import java.util.Random;

/**
 * @ClassName KeyUtil
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/18 16:29
 **/
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式:时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {

        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        System.out.println(genUniqueKey());
    }
}

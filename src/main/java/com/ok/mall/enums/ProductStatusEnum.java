package com.ok.mall.enums;

import lombok.Getter;

/**
 * @ClassName ProductStatusEnum
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/16 13:52
 **/
@Getter
public enum ProductStatusEnum {

    UP(0,"已上架"),
    DOWN(1,"未上架")
    ;
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    ProductStatusEnum(Integer code ,String message) {
        this.code = code;
        this.message = message;
    }
}

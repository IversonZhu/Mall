package com.ok.mall.enums;

import lombok.Getter;

/**
 * @ClassName PayStatusEnum
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 16:01
 **/
@Getter
public enum PayStatusEnum {
    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功")
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

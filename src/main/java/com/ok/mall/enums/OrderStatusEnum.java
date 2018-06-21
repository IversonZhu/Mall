package com.ok.mall.enums;

import lombok.Getter;

/**
 * @ClassName OrderStatusEnum
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 15:55
 **/
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

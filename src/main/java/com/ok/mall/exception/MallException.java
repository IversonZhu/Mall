package com.ok.mall.exception;

import com.ok.mall.enums.ResultEnum;

/**
 * @ClassName MallException
 * @Description 自定义异常处理
 * @Author Iverson
 * @Date 2018/6/18 16:16
 **/
public class MallException extends RuntimeException {

    private Integer code;

    public MallException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}

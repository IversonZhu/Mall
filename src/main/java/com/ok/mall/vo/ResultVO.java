package com.ok.mall.vo;

import lombok.Data;

/**
 * @ClassName ResultVo
 * @Description 请求返回的最外层对象
 * @Author Iverson
 * @Date 2018/6/16 23:05
 **/
@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;

}

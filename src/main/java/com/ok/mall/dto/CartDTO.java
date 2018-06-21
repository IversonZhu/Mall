package com.ok.mall.dto;

import lombok.Data;

/**
 * @ClassName CartDTO
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/19 9:37
 **/
@Data
public class CartDTO {

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    /**
     * CartDTO的构建方法
     * @param productId
     * @param productQuantity
     */
    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

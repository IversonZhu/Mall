package com.ok.mall.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ProductInfoVO
 * @Description 商品详情VO
 * @Author Iverson
 * @Date 2018/6/16 23:27
 **/
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}

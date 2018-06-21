package com.ok.mall.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ProductVO
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/16 23:23
 **/
@Data
public class ProductVO {

    /**
     * 商品类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 商品类目编号
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 商品列表
     */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}

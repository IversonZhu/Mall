package com.ok.mall.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @ClassName ProductInfoDO
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/16 1:55
 **/
@Table(name = "mall_product_info")
@Entity
@Data
public class ProductInfoDO {

    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品的价格
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品图片
     */
    private String productIcon;

    /**
     * 商品状态,0为正常:1为下架
     */
    private Integer productStatus;

    /**
     * 商品类目标号
     */
    private Integer categoryType;
}

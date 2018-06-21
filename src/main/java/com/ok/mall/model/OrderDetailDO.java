package com.ok.mall.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @ClassName OrderDetailDO
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 16:04
 **/
@Entity
@Data
@Table(name = "mall_order_detail")
public class OrderDetailDO {

    /**
     * 订单详情Id
     */
    @Id
    private String detailId;

    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 商品图片
     */

    private String productIcon;

}

package com.ok.mall.dto;

import com.ok.mall.enums.OrderStatusEnum;
import com.ok.mall.enums.PayStatusEnum;
import com.ok.mall.model.OrderDetailDO;
import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderDTO
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 19:05
 **/
@Data
public class OrderDTO {

    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 买家昵称
     */
    private String buyerName;

    /**
     * 买家手机号码
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家OpenId
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态,默认为0,新下单:1,完结:2,已取消
     */
    private Integer orderStatus;

    /**
     * 支付状态,默认为0,未支付:1,支付成功
     */
    private Integer payStatus;

    /**
     * 订单详情列表
     */
    private List<OrderDetailDO> orderDetailDOList;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}

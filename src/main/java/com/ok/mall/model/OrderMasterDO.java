package com.ok.mall.model;

import com.ok.mall.enums.OrderStatusEnum;
import com.ok.mall.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderMaster
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 15:47
 **/
@Entity
@Data
@Table(name = "mall_order_master")
@DynamicUpdate
public class OrderMasterDO {

    /**
     * 订单Id
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态,默认为0,未支付:1,支付成功
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}

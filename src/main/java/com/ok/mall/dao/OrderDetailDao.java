package com.ok.mall.dao;

import com.ok.mall.model.OrderDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName OrderDetailDao
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 18:28
 **/
public interface OrderDetailDao extends JpaRepository<OrderDetailDO, String> {

    /**
     * 根据订单编号查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetailDO> findByOrderId(String orderId);
}

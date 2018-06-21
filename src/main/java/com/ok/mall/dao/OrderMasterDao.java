package com.ok.mall.dao;

import com.ok.mall.model.OrderMasterDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName OrderMaster
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 16:11
 **/
public interface OrderMasterDao  extends JpaRepository<OrderMasterDO,String> {

    /**
     * 根据买家的openid分页查询用户的订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMasterDO> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}

package com.ok.mall.dao;

import com.ok.mall.model.OrderMasterDO;
import com.ok.mall.utils.UuidUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String OPENID = "10086";

    @Test
    public void save() {
        OrderMasterDO orderMasterDO = new OrderMasterDO();
        orderMasterDO.setOrderId(UuidUtils.getUuid());
        orderMasterDO.setBuyerName("Iverson");
        orderMasterDO.setBuyerPhone("13822528774");
        orderMasterDO.setBuyerAddress("湛江赤坎");
        orderMasterDO.setBuyerOpenid("10086");
        orderMasterDO.setOrderAmount(new BigDecimal(88.8));

        OrderMasterDO result = orderMasterDao.save(orderMasterDO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0,10);
        Page<OrderMasterDO> orderMasterDOPage =  orderMasterDao.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,orderMasterDOPage.getTotalElements());
    }
}
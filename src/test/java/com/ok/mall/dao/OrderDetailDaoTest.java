package com.ok.mall.dao;

import com.ok.mall.model.OrderDetailDO;
import com.ok.mall.utils.UuidUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest() {
        OrderDetailDO orderDetailDO = new OrderDetailDO();
        orderDetailDO.setDetailId(UuidUtils.getUuid());
        orderDetailDO.setOrderId("1d05f8f9c2974acba5edd1eec13fd210");
        orderDetailDO.setProductIcon("http://Iverson.jpg");
        orderDetailDO.setProductId(UuidUtils.getUuid());
        orderDetailDO.setProductName("蔬菜养生粥");
        orderDetailDO.setProductPrice(new BigDecimal(7.5));
        orderDetailDO.setProductQuantity(2);
        OrderDetailDO result = orderDetailDao.save(orderDetailDO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetailDO> orderDetailDOList = orderDetailDao.findByOrderId("1d05f8f9c2974acba5edd1eec13fd210");
        Assert.assertNotEquals(0,orderDetailDOList.size());
    }
}
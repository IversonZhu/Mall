package com.ok.mall.service.impl;

import com.ok.mall.dto.OrderDTO;
import com.ok.mall.enums.OrderStatusEnum;
import com.ok.mall.enums.PayStatusEnum;
import com.ok.mall.enums.ResultEnum;
import com.ok.mall.model.OrderDetailDO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPENID = "120120";

    private final String ORDERID = "1529547722797138912";

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Iverson");
        orderDTO.setBuyerAddress("湛江市赤坎区");
        orderDTO.setBuyerPhone("13822528774");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetailDO> orderDetailDOList = new ArrayList<>();
        OrderDetailDO orderDetailDO = new OrderDetailDO();
        orderDetailDO.setProductId("1cce20c38a8241de88f92af5cffe0dbc");
        orderDetailDO.setProductQuantity(2);

        OrderDetailDO orderDetailDO1 = new OrderDetailDO();
        orderDetailDO1.setProductId("ad2a871e65534c2cae6428724cd2de5f");
        orderDetailDO1.setProductQuantity(2);

        orderDetailDOList.add(orderDetailDO);
        orderDetailDOList.add(orderDetailDO1);
        orderDTO.setOrderDetailDOList(orderDetailDOList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDERID);
        log.info("【查询单个订单】 result={}", result);
        Assert.assertEquals(ORDERID, result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderDTO> orderDTOPage = orderService.findList("120120", pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(result.getOrderStatus(), OrderStatusEnum.CANCEL.getCode());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(result.getOrderStatus(), OrderStatusEnum.FINISHED.getCode());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(result.getPayStatus(), PayStatusEnum.SUCCESS.getCode());
    }

    @Test
    public void list() {
        PageRequest request = new PageRequest(0,10);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }
}
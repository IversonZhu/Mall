package com.ok.mall.service.impl;

import com.ok.mall.convertor.OrderMasterDO2OrderDTOConvertor;
import com.ok.mall.dao.OrderDetailDao;
import com.ok.mall.dao.OrderMasterDao;
import com.ok.mall.dto.CartDTO;
import com.ok.mall.dto.OrderDTO;
import com.ok.mall.enums.OrderStatusEnum;
import com.ok.mall.enums.PayStatusEnum;
import com.ok.mall.enums.ResultEnum;
import com.ok.mall.exception.MallException;
import com.ok.mall.model.OrderDetailDO;
import com.ok.mall.model.OrderMasterDO;
import com.ok.mall.model.ProductInfoDO;
import com.ok.mall.service.OrderService;
import com.ok.mall.service.ProductInfoService;
import com.ok.mall.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 19:11
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1.查询商品(数量,价格)
        for (OrderDetailDO orderDetailDO : orderDTO.getOrderDetailDOList()) {
            ProductInfoDO productInfoDO =  productInfoService.findOne(orderDetailDO.getProductId());
            if (productInfoDO == null) {
                throw new MallException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfoDO.getProductPrice()
                    .multiply(new BigDecimal(orderDetailDO.getProductQuantity()))
                    .add(orderAmount);
            //3.订单详情入库
            orderDetailDO.setDetailId(KeyUtil.genUniqueKey());
            orderDetailDO.setOrderId(orderId);
            BeanUtils.copyProperties(productInfoDO,orderDetailDO);
            orderDetailDao.save(orderDetailDO);
        }

        //3.写入订单数据库(OrderMaster,OrderDetail)
        OrderMasterDO orderMasterDO = new OrderMasterDO();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMasterDO);
        orderMasterDO.setOrderAmount(orderAmount);
        orderMasterDO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterDO.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMasterDO);
        //4.下单成功,扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailDOList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMasterDO orderMasterDO = orderMasterDao.findOne(orderId);
        if (orderMasterDO == null) {
            throw new MallException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetailDO> orderDetailDOList = orderDetailDao.findByOrderId(orderId);
       if (CollectionUtils.isEmpty(orderDetailDOList)) {
           throw new MallException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
       }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterDO,orderDTO);
        orderDTO.setOrderDetailDOList(orderDetailDOList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMasterDO> orderMasterDOPage = orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMasterDO2OrderDTOConvertor.convertor(orderMasterDOPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterDOPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMasterDO orderMasterDO = new OrderMasterDO();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("【取消订单】订单状态不正确 orderId={}", orderDTO.getOrderId());
            throw new MallException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMasterDO);
        OrderMasterDO updateResult = orderMasterDao.save(orderMasterDO);
        if (updateResult == null) {
            log.info("【取消订单】更新订单失败 orderMaster={}" ,orderMasterDO);
            throw new MallException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailDOList())) {
            log.info("【取消订单】订单详情为空",orderDTO.getOrderDetailDOList().size());
            throw new MallException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailDOList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //如果已支付就需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("【完结订单】订单状态不正确orderStatus={}", orderDTO.getOrderStatus());
            throw new MallException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMasterDO orderMasterDO = new OrderMasterDO();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMasterDO);
        OrderMasterDO updateResult = orderMasterDao.save(orderMasterDO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("【订单支付】订单状态不正确 orderStatus={}", orderDTO.getOrderStatus());
            throw new MallException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.info("【订单支付】支付状态不正确 payStatus={}", orderDTO.getPayStatus());
            throw new MallException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMasterDO orderMasterDO = new OrderMasterDO();
        BeanUtils.copyProperties(orderDTO,orderMasterDO);
        OrderMasterDO updateResult = orderMasterDao.save(orderMasterDO);
        if (updateResult == null) {
            log.info("【订单支付】更新失败 orderMaster={}", orderMasterDO);
            throw new MallException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMasterDO> orderMasterDOPage = orderMasterDao.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMasterDO2OrderDTOConvertor.convertor(orderMasterDOPage.getContent());
        return new PageImpl<>(orderDTOList,pageable,orderMasterDOPage.getTotalElements());
    }
}

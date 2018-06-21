package com.ok.mall.convertor;

import com.ok.mall.dto.OrderDTO;
import com.ok.mall.model.OrderMasterDO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderMaster2OrderDTOConvertor
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/21 10:46
 **/
public class OrderMasterDO2OrderDTOConvertor {

    public static OrderDTO convertor(OrderMasterDO orderMasterDO) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterDO,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convertor(List<OrderMasterDO> orderMasterDOList) {
        return orderMasterDOList.stream().map(e ->
                convertor(e)
        ).collect(Collectors.toList());
    }
}

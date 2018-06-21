package com.ok.mall.service.impl;

import com.ok.mall.enums.ProductStatusEnum;
import com.ok.mall.model.ProductInfoDO;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfoDO productInfoDO = productInfoService.findOne("1cce20c38a8241de88f92af5cffe0dbc");
        Assert.assertEquals("1cce20c38a8241de88f92af5cffe0dbc",productInfoDO.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfoDO> productInfoDOList = productInfoService.findUpAll();
        Assert.assertNotEquals(0,productInfoDOList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,3);
        Page<ProductInfoDO> productInfoDOPage =  productInfoService.findAll(request);
        Assert.assertNotEquals(0,productInfoDOPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfoDO productInfoDO = new ProductInfoDO();
        productInfoDO.setProductId(UuidUtils.getUuid());
        productInfoDO.setProductName("皮皮虾");
        productInfoDO.setProductPrice(new BigDecimal(9.9));
        productInfoDO.setProductStock(100);
        productInfoDO.setProductDescription("很好吃的虾");
        productInfoDO.setProductIcon("http://pipixia.jpg");
        productInfoDO.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoDO.setCategoryType(2);
        ProductInfoDO result = productInfoService.save(productInfoDO);
        Assert.assertNotNull(result);
    }
}
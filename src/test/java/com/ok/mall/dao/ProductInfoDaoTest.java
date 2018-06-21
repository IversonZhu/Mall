package com.ok.mall.dao;

import com.ok.mall.model.ProductInfoDO;
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
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void findOneTest() {

    }

    @Test
    public void save() {
        ProductInfoDO productInfoDO = new ProductInfoDO();
        productInfoDO.setProductId(UuidUtils.getUuid());
        productInfoDO.setProductName("皮蛋瘦肉粥");
        productInfoDO.setProductPrice(new BigDecimal(9.9));
        productInfoDO.setProductStock(100);
        productInfoDO.setProductDescription("很好喝的粥");
        productInfoDO.setProductIcon("http://pidanshourouzhou.jpg");
        productInfoDO.setProductStatus(0);
        productInfoDO.setCategoryType(1);

        ProductInfoDO result = productInfoDao.save(productInfoDO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfoDO> list = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }
}
package com.ok.mall.service.impl;

import com.ok.mall.model.ProductCategoryDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findOne() {
        ProductCategoryDO productCategoryDO =  productCategoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategoryDO.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategoryDO> productCategoryDOList = productCategoryService.findAll();
        Assert.assertNotEquals(0,productCategoryDOList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategoryDO> productCategoryDOList = productCategoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0,productCategoryDOList.size());
    }

    @Test
    public void save() {
        ProductCategoryDO productCategoryDO = new ProductCategoryDO("男生专线",4);
        ProductCategoryDO result = productCategoryService.save(productCategoryDO);
        Assert.assertNotNull(result);
    }
}
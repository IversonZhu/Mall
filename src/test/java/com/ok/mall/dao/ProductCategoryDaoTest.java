package com.ok.mall.dao;

import com.ok.mall.model.ProductCategoryDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest() {
        ProductCategoryDO productCategoryDO = productCategoryDao.findOne(1);
        System.out.println(productCategoryDO.toString());
    }

    @Test
    public void createOneTest() {
        ProductCategoryDO productCategoryDO = new ProductCategoryDO("男生最爱",3);
        ProductCategoryDO result = productCategoryDao.save(productCategoryDO);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional//测试环境不插表
    public void updateOneTest() {
        ProductCategoryDO productCategoryDO = productCategoryDao.findOne(2);
        productCategoryDO.setCategoryName("女生最爱");
        productCategoryDO.setCategoryType(2);
        ProductCategoryDO result =  productCategoryDao.save(productCategoryDO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategoryDO> result =  productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}
package com.ok.mall.service.impl;

import com.ok.mall.dao.ProductCategoryDao;
import com.ok.mall.model.ProductCategoryDO;
import com.ok.mall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductCategoryServiceImpl
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/15 17:35
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategoryDO findOne(Integer categoryId) {
        return productCategoryDao.findOne(categoryId);
    }

    @Override
    public List<ProductCategoryDO> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategoryDO> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategoryDO save(ProductCategoryDO productCategoryDO) {
        return productCategoryDao.save(productCategoryDO);
    }
}

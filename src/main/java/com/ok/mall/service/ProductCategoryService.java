package com.ok.mall.service;

import com.ok.mall.model.ProductCategoryDO;

import java.util.List;

/**
 * @ClassName ProductCategoryService
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/15 17:35
 **/
public interface ProductCategoryService {

    /**
     * 根据id查询商品类目信息
     * @return
     */
    ProductCategoryDO findOne(Integer categoryId);

    /**
     * 查询所有类目信息
     * @return
     */
    List<ProductCategoryDO> findAll();

    /**
     *根据商品类目编号列表查询商品类目信息
     * @param categoryTypeList
     * @return
     */
    List<ProductCategoryDO> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增\修改
     * @param productCategoryDO
     * @return
     */
    ProductCategoryDO save(ProductCategoryDO productCategoryDO);
}

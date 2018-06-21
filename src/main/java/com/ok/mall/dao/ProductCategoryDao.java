package com.ok.mall.dao;

import com.ok.mall.model.ProductCategoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ProductCategoryDao
 * @Description 商品类目的Dao接口
 * @Author Iverson
 * @Date 2018/6/15 13:30
 **/
public interface ProductCategoryDao extends JpaRepository<ProductCategoryDO,Integer> {

    List<ProductCategoryDO> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

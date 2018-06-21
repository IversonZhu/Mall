package com.ok.mall.dao;

import com.ok.mall.model.ProductInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ProductInfoDao
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/16 2:03
 **/
public interface ProductInfoDao extends JpaRepository<ProductInfoDO,String> {

    /**
     * 根据商品的状态查询商品列表
     * @param productStatus
     * @return
     */
    List<ProductInfoDO> findByProductStatus(Integer productStatus);
}

package com.ok.mall.service;

import com.ok.mall.dto.CartDTO;
import com.ok.mall.model.ProductInfoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ClassName ProductInfoService
 * @Description 商品信息接口
 * @Author Iverson
 * @Date 2018/6/16 13:47
 **/
public interface ProductInfoService {

    /**
     * 根据商品Id查询商品信息
     * @param productId
     * @return
     */
    ProductInfoDO findOne(String productId);

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfoDO> findUpAll();

    /**
     * 分页查询所有商品
     * @param pageable
     * @return
     */
    Page<ProductInfoDO> findAll(Pageable pageable);

    /**
     * 新增\修改商品信息
     * @param productInfoDO
     * @return
     */
    ProductInfoDO save(ProductInfoDO productInfoDO);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);
    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}

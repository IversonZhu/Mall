package com.ok.mall.service.impl;

import com.ok.mall.dao.ProductInfoDao;
import com.ok.mall.dto.CartDTO;
import com.ok.mall.enums.ProductStatusEnum;
import com.ok.mall.enums.ResultEnum;
import com.ok.mall.exception.MallException;
import com.ok.mall.model.ProductInfoDO;
import com.ok.mall.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName ProductInfoServiceImpl
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/16 13:51
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfoDO findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfoDO> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfoDO> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfoDO save(ProductInfoDO productInfoDO) {
        return productInfoDao.save(productInfoDO);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfoDO productInfoDO = productInfoDao.findOne(cartDTO.getProductId());
            if (productInfoDO == null) {
                throw new MallException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfoDO.getProductStock() + cartDTO.getProductQuantity();
            productInfoDO.setProductStock(result);

            productInfoDao.save(productInfoDO);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfoDO productInfoDO = productInfoDao.findOne(cartDTO.getProductId());
            if (productInfoDO == null) {
                throw new MallException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfoDO.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new MallException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfoDO.setProductStock(result);

            productInfoDao.save(productInfoDO);
        }
    }
}

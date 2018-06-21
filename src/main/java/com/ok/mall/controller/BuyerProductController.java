package com.ok.mall.controller;

import com.ok.mall.model.ProductCategoryDO;
import com.ok.mall.model.ProductInfoDO;
import com.ok.mall.service.ProductCategoryService;
import com.ok.mall.service.ProductInfoService;
import com.ok.mall.utils.ResultVOUtil;
import com.ok.mall.vo.ProductInfoVO;
import com.ok.mall.vo.ProductVO;
import com.ok.mall.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BuyerProductController
 * @Description 买家商品
 * @Author Iverson
 * @Date 2018/6/16 23:00
 **/

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有上架的商品
        List<ProductInfoDO> productInfoDOList = productInfoService.findUpAll();
        //2.查询类目
        List<Integer> categoryTypeList = new ArrayList<>();
        //2.1.传统方法获取categoryType
       for (ProductInfoDO productInfoDO : productInfoDOList) {
            categoryTypeList.add(productInfoDO.getCategoryType());
        }
        //2.2.精简的方法(java8的特性,lambda)
        //List<Integer> categoryTypeList = productInfoDOList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategoryDO> productCategoryDOList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据的拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategoryDO productCategoryDO : productCategoryDOList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategoryDO.getCategoryName());
            productVO.setCategoryType(productCategoryDO.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfoDO productInfoDO : productInfoDOList) {
                if (productInfoDO.getCategoryType().equals(productCategoryDO.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfoDO, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return  ResultVOUtil.success(productVOList);
    }
}

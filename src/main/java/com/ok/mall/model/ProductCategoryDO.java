package com.ok.mall.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName ProductCategoryDO
 * @Description 商品类目
 * @Author Iverson
 * @Date 2018/6/15 11:43
 * @Table MALL_PRODUCT_CATEGORY
 **/
@Table(name = "mall_product_category")
@Entity
@DynamicUpdate
@Data
public class ProductCategoryDO {
    /**
     * 商品类目id
     */
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * 商品类目名称
     */
    private String categoryName;
    /**
     * 商品类目编号
     */
    private Integer categoryType;

    public ProductCategoryDO() {}

    /**
     * 构造方法
     * @param categoryName
     * @param categoryType
     */
    public ProductCategoryDO(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "ProductCategoryDO{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }
}

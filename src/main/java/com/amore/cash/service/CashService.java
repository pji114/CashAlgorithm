package com.amore.cash.service;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;

import java.util.List;

public interface CashService {

    /**
     * @date : 2022-03-14 오후 2:51
     * @description : 카테고리 전체 조회
    */
    List<Category> findAllCategories();

    /**
     * @date : 2022-03-14 오후 2:51
     * @description : 제품 전체조회
    */
    List<Product> findAllProducts();

    /**
     * @date : 2022-03-14 오후 2:52
     * @description : 카테고리 단건 조회
    */
    Category findByCashCategory(Long categoryNo);

    /**
     * @date : 2022-03-14 오후 2:51
     * @description : case1. 특정 상품에 대하여 상품명, 카테고리, 가격을 조회
    */
    Product findByCashProduct(Long productNo);

    /**
     * @date : 2022-03-14 오후 2:51
     * @description : case1. 특정 카테고리에 속한 상품 리스트
    */
    List<Product> findByCashProductByCategory(Long categoryNo);

    /**
     * @date : 2022-03-14 오후 2:51
     * @description : case4. 카테고리명 변경
    */
    Category updateCategoryName(Long categoryNo, String categoryName);

    /**
     * @date : 2022-03-14 오후 2:51
     * @description : case4. 특정 상품의 가격 & 상품명 변경
    */
    Product updateProduct(Long productNo, Product productParam);

}

package com.amore.cash.service;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;

import java.util.List;

public interface CashService {

    List<Category> findAllCategories();

    List<Product> findAllProducts();

    Category findByCashCategory(Long categoryNo);

    //case1. 특정 상품에 대하여 상품명, 카테고리, 가격을 조회
    Product findByCashProduct(Long productNo);

    //case1. 특정 카테고리에 속한 상품 리스트
    List<Product> findByCashProductByCategory(Long categoryNo);

    //case4. 카테고리명 변경
    Category updateCategoryName(Long categoryNo, String categoryName);

    //case4. 특정 상품의 가격 & 상품명 변경
    Product updateProduct(Long productNo, Product productParam);

}

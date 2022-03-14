package com.amore.cash.utils;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * In Memory Cash 로 사용할 카테고리, 제품에 대한 Static LinkedHashMap
 */
public class CashMap {

    public static Map<Long, Category> CATEGORY_MAP = new LinkedHashMap<>();
    public static Map<Long, Product> PRODUCT_MAP = new LinkedHashMap<>();

}

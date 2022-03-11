package com.amore.cash.utils;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class CashMap {

    public static Map<Long, Category> CATEGORY_MAP = new LinkedHashMap<>();
    public static Map<Long, Product> PRODUCT_MAP = new LinkedHashMap<>();

}

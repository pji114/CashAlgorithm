package com.amore.cash.utils;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * case3. Cache Data Eviction Policy
 * Eviction Policy : LRU
 * 웹 페이지에서 고객(사람)이 사용하는 기능이기 불규칙적으로 대상이 선택된다 따라서 FIFO 나 LIFO 는 적합하지 않다.
 * 사용 빈도 수에 따른 캐시 정책이 올바르다고 판단하여 LRU 를 적용한다.
 * LinkedHashMap 에서 제공하는 LRU 기능인 removeEldestEntry 상속받아 구현한다.
 */
public class ProductCashMap extends LinkedHashMap<Integer, Product> {

    int capacity = 0;

    public ProductCashMap(int capacity) {
        super(capacity, 0.75f, true); //성능을 위해 load factor 는 75%을 유지한다.
        this.capacity = capacity;
    }

    /**
     * @date : 2022-03-11 오후 4:47
     * @description : LinkedHashMap 에서 제공하는 LRU 를 사용해서 가장 적게 사용된 데이터를 삭제한다.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Product> eldest) {
        return size() > capacity;
    }

}

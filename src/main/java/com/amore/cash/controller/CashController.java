package com.amore.cash.controller;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.packet.CommonResponse;
import com.amore.cash.service.impl.CashServiceImpl;
import com.amore.cash.utils.CashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@Slf4j
@RestController
@RequestMapping("/cash")
public class CashController {

    private final CashServiceImpl cashService;

    public CashController(CashServiceImpl cashService) {
        this.cashService = cashService;
    }


    /**
     * @author : SKMG
     * @date : 2022-03-11 오후 6:10
     * @description : 카테고리 및 제품 전체 조회
    */
    @GetMapping("")
    public String getCategoriesInfo(){

        log.info("=============Category=================");
        Iterator<Long> keys = CashMap.CATEGORY_MAP.keySet().iterator();
        while(keys.hasNext()) {
            Long key = keys.next();
            log.info("Key : "+key);
            log.info("Value : "+CashMap.CATEGORY_MAP.get(key).toString());
        }


        log.info("=============Product=================");
        Iterator<Long> productKeys = CashMap.PRODUCT_MAP.keySet().iterator();
        while(productKeys.hasNext()) {
            Long key = productKeys.next();
            log.info("Key : "+key);
            log.info("Value : "+CashMap.PRODUCT_MAP.get(key).toString());
        }

        return "dddd";
    }

    /**
     * @author : SKMG
     * @date : 2022-03-11 오후 6:10
     * @description : 카테고리 단건 조회
    */
    @GetMapping("/category")
    public CommonResponse getCategoryInfo(@RequestParam(value="categoryNo") int categoryNo){

        return CommonResponse.builder()
                .data(this.cashService.findByCashCategory(Long.valueOf(categoryNo)))
                .build();
    }
}
package com.amore.cash.controller;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;
import com.amore.cash.packet.CategoryRequest;
import com.amore.cash.packet.CommonResponse;
import com.amore.cash.service.impl.CashServiceImpl;
import com.amore.cash.utils.CashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cash")
public class CashController {

    //todo : swagger

    private final CashServiceImpl cashService;

    public CashController(CashServiceImpl cashService) {
        this.cashService = cashService;
    }


    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 카테고리 전체 조회
    */
    @GetMapping("categories")
    public List<Category> getCategories(){
        return this.cashService.findAllCategories();
    }

    /**
     * @date : 2022-03-11 오후 8:34
     * @description : 제품 전체 조회
     */
    @GetMapping("products")
    public List<Product> getProducts(){
        return this.cashService.findAllProducts();
    }

    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 카테고리 단건 조회
    */
    @GetMapping("category")
    public Category getCategory(@RequestParam(value="categoryNo") int categoryNo){

        return this.cashService.findByCashCategory(Long.valueOf(categoryNo));
    }

    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 제품 단건 조회
     */
    @GetMapping("product")
    public Product getProduct(@RequestParam(value="productNo") int productNo) {
        return this.cashService.findByCashProduct(Long.valueOf(productNo));
    }

    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 특정 카테고리에 대한 제품 리스트
     * case 1
     */
    @GetMapping("product/category")
    public List<Product> getProductByCategory(@RequestParam(value="categoryNo") int categoryNo){
        return this.cashService.findByCashProductByCategory(Long.valueOf(categoryNo));
    }

    /**
     * @date : 2022-03-11 오후 10:00
     * @description : 특정 카테고리명을 변경
     * case 4
     */
    @PutMapping("category-name")
    public Category putCategoryName(@RequestParam(value="categoryNo") int categoryNo,
                                @RequestBody CategoryRequest categoryRequest) {
        return this.cashService.updateCategoryName(Long.valueOf(categoryNo), categoryRequest.getCategoryName());
    }

    /**
     * @date : 2022-03-11 오후 10:15
     * @description : 특정 상품의 가격 & 상품명 변경
     * case 4
     */
    @PutMapping("product")
    public Product putProduct(@RequestParam(value="productNo") int productNo,
                              @RequestBody Product product){
        return this.cashService.updateProduct(Long.valueOf(productNo), product);
    }
}
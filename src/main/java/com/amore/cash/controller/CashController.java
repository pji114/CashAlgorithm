package com.amore.cash.controller;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;
import com.amore.cash.packet.CategoryRequest;
import com.amore.cash.service.impl.CashServiceImpl;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/cash")
public class CashController {

    private final CashServiceImpl cashService;

    public CashController(CashServiceImpl cashService) {
        this.cashService = cashService;
    }

    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 카테고리 전체 조회
     */
    @Operation(summary = "전체 카테고리 조회", description = "전체 카테고리 조회")
    @GetMapping("categories")
    public Map<Long, Category> getCategories() {
        return this.cashService.findAllCategories();
    }

    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 카테고리 단건 조회
     */
    @Operation(summary = "카테고리 단건 조회", description = "카테고리 단건 조회")
    @GetMapping("category")
    public Category getCategory(
            @ApiParam(value = "카테고리 번호", required = true)
            @RequestParam(value = "categoryNo") int categoryNo) {

        return this.cashService.findByCashCategory(Long.valueOf(categoryNo));
    }

    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 특정 카테고리에 대한 제품 리스트
     * case 1
     */
    @Operation(summary = "특정 카테고리에 대한 제품 리스트", description = "특정 카테고리에 대한 제품 리스트")
    @GetMapping("product/category")
    public List<Product> getProductByCategory(
            @ApiParam(value = "카테고리 번호", required = true)
            @RequestParam(value = "categoryNo") int categoryNo) {
        return this.cashService.findByCashProductByCategory(Long.valueOf(categoryNo));
    }

    /**
     * @date : 2022-03-11 오후 10:00
     * @description : 특정 카테고리명을 변경
     * case 4
     */
    @Operation(summary = "특정 카테고리명 변경", description = "특정 카테고리명 변경 \n ※카테고리 번호만 전송한다")
    @PutMapping("category-name")
    public Category putCategoryName(
            @ApiParam(value = "카테고리 번호", required = true)
            @RequestParam(value = "categoryNo") int categoryNo,

            @RequestBody CategoryRequest categoryRequest) {
        return this.cashService.updateCategoryName(Long.valueOf(categoryNo), categoryRequest.getCategoryName());
    }

    /**
     * @date : 2022-03-11 오후 8:34
     * @description : 제품 전체 조회
     */
    @Operation(summary = "제품 전체조회", description = "제품 전체조회")
    @GetMapping("products")
    public Map<Long, Product> getProducts() {
        return this.cashService.findAllProducts();
    }

    /**
     * @date : 2022-03-11 오후 6:10
     * @description : 제품 단건 조회
     */
    @Operation(summary = "제품 단건조회", description = "제품 단건조회")
    @GetMapping("product")
    public Product getProduct(
            @ApiParam(value = "제품번호", required = true)
            @RequestParam(value = "productNo") int productNo) {
        return this.cashService.findByCashProduct(Long.valueOf(productNo));
    }

    /**
     * @date : 2022-03-11 오후 10:15
     * @description : 특정 상품의 가격 & 상품명 변경
     * case 4
     */
    @Operation(summary = "특정 제품의 가격 & 제품명 변경", description = "특정 상품에 대한 제품가격 및 제품명 변경 \n ※ 상품가격 및 제품명만 전송한다")
    @PutMapping("product")
    public Product putProduct(
            @ApiParam(value = "제품 번호", required = true)
            @RequestParam(value = "productNo") int productNo,

            @RequestBody Product product) {
        return this.cashService.updateProduct(Long.valueOf(productNo), product);
    }
}
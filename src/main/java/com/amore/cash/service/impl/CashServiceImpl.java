package com.amore.cash.service.impl;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;
import com.amore.cash.amoredb.repository.CategoryRepository;
import com.amore.cash.amoredb.repository.ProductRepository;
import com.amore.cash.service.CashService;
import com.amore.cash.utils.CashMap;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * case 4. Cache Optimization
 */
@Service
public class CashServiceImpl implements CashService {

    /**
     * Constructor Injection
     */
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CashServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    /**
     * @date : 2022-03-11 오후 8:31
     * @description : 전체 조회는 cash 에서 조회하면 값의 오차가 있을수 있기 때문에 DB 에서 직접 가져온다.
     */
    @Override
    public List<Category> findAllCategories() {
        return this.categoryRepository.findAll();
    }

    /**
     * @date : 2022-03-11 오후 8:31
     * @description : 전체 조회는 cash 에서 조회하면 값의 오차가 있을수 있기 때문에 DB 에서 직접 가져온다.
     */
    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }


    /**
     * @date : 2022-03-11 오후 5:47
     * @description : Cash 에서 Category 를 조회한다.
     * (조회) cash 조회시 값이 없으면 db 에서 값을 가져온 후 cash 에 해당 값을 채워넣는다.
     */
    @Override
    public Category findByCashCategory(Long categoryNo) {

        Category category = new Category();

        //cash hit
        if (CashMap.CATEGORY_MAP.containsKey(categoryNo)) {
            category = CashMap.CATEGORY_MAP.get(categoryNo);

        } else { //cash miss

            Category dbCategory = this.categoryRepository.findById(categoryNo).get(); //db 에서 값 조회

            if (dbCategory != null) {
                CashMap.CATEGORY_MAP.put(categoryNo, dbCategory); //조회된 값 cash 에 삽입
            }
        }

        return category;
    }

    /**
     * @date : 2022-03-11 오후 8:47
     * @description : Cash 에서 Category 를 조회한다.
     * (조회) cash 조회시 값이 없으면 db 에서 값을 가져온 후 cash 에 해당 값을 채워넣는다.
     */
    @Override
    public Product findByCashProduct(Long productNo) {

        Product product = new Product();

        //cash hit
        if (CashMap.PRODUCT_MAP.containsKey(productNo)) {
            product = CashMap.PRODUCT_MAP.get(productNo);

        } else { //cash miss

            Product dbProduct = this.productRepository.findById(productNo).get(); //db 에서 값 조회

            if (dbProduct != null) {
                CashMap.PRODUCT_MAP.put(productNo, dbProduct);
            }
        }

        return product;
    }

    @Override
    public List<Product> findByCashProductByCategory(Long categoryNo) {

        List<Product> products = new ArrayList<>();

        Iterator<Long> keys = CashMap.PRODUCT_MAP.keySet().iterator();
        while (keys.hasNext()) {
            Long key = keys.next();

            if (Long.valueOf(CashMap.PRODUCT_MAP.get(key).getCategoryNo()) == categoryNo) {
                products.add(CashMap.PRODUCT_MAP.get(key));
            }
        }
        return products;
    }

    /**
     * @date : 2022-03-14 오후 3:25
     * @description : jpa save 는 값이 없을 때는 새롭게 삽입을 하기 때문에 update 만 하는 method 에서는 맞지않다. 따러서 기존에 값이 있는지 먼저 검증하고 있을때만 save (only Update)를 실행한다.
     * DB에 값을 갱신하면 해당 값을 Cash 에 put 한다.
     */
    @Override
    @Transactional
    public Category updateCategoryName(Long categoryNo, String categoryName) {

        Category category = this.categoryRepository.findById(categoryNo).get();

        if (category != null) { // 값이 있을때만 save 한다.
            Category cashCategory = CashMap.CATEGORY_MAP.get(Long.valueOf(categoryNo));

            category.setCategoryName(categoryName);
            cashCategory.setCategoryName(categoryName);

            //jpa save
            this.categoryRepository.save(category);

            //cash update
            CashMap.CATEGORY_MAP.put(categoryNo, cashCategory);
        }
        return category;
    }

    /**
     * @date : 2022-03-14 오후 3:25
     * @description : jpa save 는 값이 없을 때는 새롭게 삽입을 하기 때문에 update만 하는 method 에서는 맞지않다. 따러서 기존에 값이 있는지 먼저 검증하고 있을때만 save (only Update)를 실행한다.
     * DB에 값을 갱신하면 해당 값을 Cash 에 put 한다.
     */
    @Override
    @Transactional
    public Product updateProduct(Long productNo, Product productParam) {

        Product product = this.productRepository.findById(productNo).get();


        if (product != null) { // 값이 있을때만 save 한다.
            Product cashProduct = CashMap.PRODUCT_MAP.get(productNo);

            if (productParam.getProductName() != null) { // 제품명
                product.setProductName(productParam.getProductName());
                cashProduct.setProductName(productParam.getProductName());
            }

            if (productParam.getProductPrice() != null) { // 제품 가격
                product.setProductPrice(productParam.getProductPrice());
                cashProduct.setProductPrice(productParam.getProductPrice());
            }

            //jpa save
            this.productRepository.save(product);

            //cash update
            CashMap.PRODUCT_MAP.put(productParam.getProductNo(), cashProduct);
        }

        return product;
    }
}

package com.amore.cash.config;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;
import com.amore.cash.amoredb.repository.CategoryRepository;
import com.amore.cash.amoredb.repository.ProductRepository;
import com.amore.cash.utils.CashMap;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/** Case2. Data Loading and Reloading
 * ApplicationListener 상속받아 구현하여 Spring 의 모든 Context 가 초기화 된 후
 * 카테고리 및 제품 정보를 DB 에서 읽어 각각의 LinkedHashMap 에 초기화 한다.
 */
@Component
public class InitConfig implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * Constructor Injection
     */
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public InitConfig(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    private void initCategoryCashMap(){
        List<Category> categoryList = this.categoryRepository.findAll();
        List<Product> productList = this.productRepository.findAll();

        for(Category category : categoryList) {
            CashMap.CATEGORY_MAP.put(category.getCategoryNo(), category);
        }

        for(Product product : productList) {
            CashMap.PRODUCT_MAP.put(product.getProductNo(), product);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.initCategoryCashMap();
    }
}

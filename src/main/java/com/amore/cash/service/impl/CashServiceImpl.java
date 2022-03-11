package com.amore.cash.service.impl;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.repository.CategoryRepository;
import com.amore.cash.amoredb.repository.ProductRepository;
import com.amore.cash.service.CashService;
import com.amore.cash.utils.CashMap;
import org.springframework.stereotype.Service;

/**
 * case 4. Cache Optimization
 * (조회) cash 조회시 값이 없으면 db 에서 값을 가져온 후 cash 에 해당 값을 채워넣는다.
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
     * @author : SKMG
     * @date : 2022-03-11 오후 5:47
     * @description : Cash 에서 Category 를 조회한다.
    */
    @Override
    public Category findByCashCategory(Long categoryNo) {

        Category category = new Category();

        //cash hit
        if(CashMap.CATEGORY_MAP.containsKey(categoryNo)) {
            category = CashMap.CATEGORY_MAP.get(categoryNo);

        } else { //cash miss

            Category dbCategory = this.categoryRepository.findById(categoryNo).get(); //db 에서 값 조회

            if(dbCategory != null) {
                CashMap.CATEGORY_MAP.put(categoryNo, dbCategory); //조회된 값 cash 에 삽입
            }
        }

        return category;
    }
}

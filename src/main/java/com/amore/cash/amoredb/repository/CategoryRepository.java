package com.amore.cash.amoredb.repository;

import com.amore.cash.amoredb.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    /**
     * @date : 2022-03-11 오후 9:42
     * @description : 카테고리 명 변경
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Category ca SET ca.categoryName = :categoryName WHERE ca.categoryNo = :categoryNo")
    int updateCategoryName(Long categoryNo, String categoryName);
}

package com.amore.cash.service;

import com.amore.cash.amoredb.domain.Category;
import com.amore.cash.amoredb.domain.Product;

public interface CashService {

    Category findByCashCategory(Long categoryNo);

}

package com.amore.cash.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Set;

/**
 * swagger 설정
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Amore Cash")
                .description("각 case 별 주요 로직 위치\n" +
                        "case 1. Restful API : CashController.java \n" +
                        "case 2. 초기 구동시 데이터 Load : InitConfig.java \n" +
                        "case 3. Cash Eviction Policy : CashMap.java, CategoryCashMap.java, ProductCashMap.java \n" +
                        "case 4. CashServiceImpl.java > findByCashCategory(..), findByCashProduct(..), updateCategoryName(..), updateProduct(..) \n" +
                        "case 5. CashServiceImpl.java > 상품명 및 가격 변경[updateProduct(..)], 카테고리명 변경[updateCategoryName(..)]")
                .version("1.0")
                .build();
    }

    private Set<String> getConsumeContentType(){
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentType(){
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    @Bean
    public Docket CommonApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentType())
                .produces(getProduceContentType())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/cash/**"))
                .build();
    }


}

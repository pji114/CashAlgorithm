package com.amore.cash.amoredb.domain;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "제품번호", required = true)
    Long productNo;

    @ApiModelProperty(value = "브랜드명", required = false)
    String brandName;

    @ApiModelProperty(value = "제품명", required = false)
    String productName;

    @ApiModelProperty(value = "제품가격", required = false)
    Integer productPrice;

    @ApiModelProperty(value = "카테고리번호", required = false)
    Integer categoryNo;
}

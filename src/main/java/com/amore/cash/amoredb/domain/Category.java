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
public class Category {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "카테고리 번호", required = true)
    Long categoryNo;

    @ApiModelProperty(value = "카테고리 이름", required = false)
    String categoryName;

    @ApiModelProperty(value = "상위 카테고리, 변경불가", required = false)
    Integer parentNo;

}

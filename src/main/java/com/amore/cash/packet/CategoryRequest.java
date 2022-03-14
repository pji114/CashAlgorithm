package com.amore.cash.packet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryRequest {

    @ApiModelProperty(value = "카테고리 번호", required = true)
    Integer categoryNo;

    @ApiModelProperty(value = "카테고리 이름", required = false)
    String categoryName;

    @ApiModelProperty(value = "상위 카테고리 번호", required = false)
    Integer parentNo;
}

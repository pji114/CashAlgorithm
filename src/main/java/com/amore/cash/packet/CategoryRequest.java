package com.amore.cash.packet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryRequest {

    Long categoryNo;

    String categoryName;

    Integer parent_no;
}

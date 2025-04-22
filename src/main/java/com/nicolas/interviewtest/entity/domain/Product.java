package com.nicolas.interviewtest.entity.domain;

import com.nicolas.interviewtest.entity.enums.ProductCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String name;
    private ProductCategoryEnum productCategoryEnum;
}

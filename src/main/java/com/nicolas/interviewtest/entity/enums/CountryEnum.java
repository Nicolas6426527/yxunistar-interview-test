package com.nicolas.interviewtest.entity.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum CountryEnum {
    CA("CA", 0.0975, List.of(ProductCategoryEnum.FOOD)),
    NY("NY", 0.08875, List.of(ProductCategoryEnum.FOOD, ProductCategoryEnum.CLOTHING)),
    OTHER("other", 0, List.of());

    private final String name;
    private final double textRate;
    private final List<ProductCategoryEnum> taxFreeProductEnums;

    CountryEnum(String name, double textRate, List<ProductCategoryEnum> taxFreeProductEnums) {
        this.name = name;
        this.textRate = textRate;
        this.taxFreeProductEnums = taxFreeProductEnums;
    }

    public static CountryEnum getByText(String name) {
        try {
            return CountryEnum.valueOf(name);
        } catch (IllegalArgumentException e) {
            return CountryEnum.OTHER;
        }
    }
}

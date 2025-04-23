package com.nicolas.interviewtest.entity.domain;

import com.nicolas.interviewtest.entity.enums.CountryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private Product product;
    private double price;
    private int quantity;
    private CountryEnum countryEnum;

    public double getTotalPrice() {
        return price * quantity;
    }

    public double getTotalTax() {
        return countryEnum.getTaxFreeProductEnums().stream()
                .anyMatch(c -> c.equals(product.getProductCategoryEnum())) ? 0.0 : round(getTotalPrice() * countryEnum.getTextRate());
    }

    private double round(Double price) {
        return BigDecimal.valueOf(price)
                .multiply(BigDecimal.valueOf(20))
                .setScale(0, RoundingMode.CEILING)
                .divide(BigDecimal.valueOf(20), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}

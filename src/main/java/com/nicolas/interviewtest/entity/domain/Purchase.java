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
        BigDecimal bigDecimal = BigDecimal.valueOf(price);
        BigDecimal multiply = bigDecimal.multiply(BigDecimal.valueOf(20));
        BigDecimal bigDecimal1 = multiply.setScale(2, RoundingMode.CEILING);
        BigDecimal divide = bigDecimal1.divide(BigDecimal.valueOf(20), RoundingMode.HALF_UP);
        BigDecimal bigDecimal2 = divide.setScale(1, RoundingMode.CEILING);
        return bigDecimal2.doubleValue();
    }
}

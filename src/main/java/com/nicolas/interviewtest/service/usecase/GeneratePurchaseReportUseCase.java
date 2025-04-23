package com.nicolas.interviewtest.service.usecase;

import com.nicolas.interviewtest.entity.enums.CountryEnum;
import com.nicolas.interviewtest.entity.domain.Product;
import com.nicolas.interviewtest.entity.enums.ProductCategoryEnum;
import com.nicolas.interviewtest.entity.domain.Purchase;

import java.util.List;

public class GeneratePurchaseReportUseCase {
    private static final List<Product> PRODUCT_LIST = List.of(
            Product.builder().name("book").productCategoryEnum(ProductCategoryEnum.OTHER).build(),
            Product.builder().name("pencil").productCategoryEnum(ProductCategoryEnum.OTHER).build(),
            Product.builder().name("potato chips").productCategoryEnum(ProductCategoryEnum.FOOD).build(),
            Product.builder().name("shirt").productCategoryEnum(ProductCategoryEnum.CLOTHING).build()
    );

    public void execute(GeneratePurchaseReportUseCaseInput input, GeneratePurchaseReportUseCaseOutput output) {
        List<Purchase> purchases = inputToDomain(input.getPurchaseInputs(), input.getCountryCode());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%-12s %10s %10s\n\n".formatted("item", "price", "qty"));
        purchases.forEach(p -> stringBuilder.append("%-12s %10s %10d\n".formatted(p.getProduct().getName(), "$%s".formatted(p.getPrice()), p.getQuantity())));
        double subtotal = purchases.stream().mapToDouble(Purchase::getTotalPrice).sum();
        double totalTax = purchases.stream().mapToDouble(Purchase::getTotalTax).sum();
        stringBuilder.append("%-12s %21s\n".formatted("subtotal:", "$%.2f".formatted(subtotal)));
        stringBuilder.append("%-12s %21s\n".formatted("tax:", "$%.2f".formatted(totalTax)));
        stringBuilder.append("%-12s %21s".formatted("total:", "$%.2f".formatted(subtotal + totalTax)));
        output.setRes(stringBuilder.toString());
    }

    private List<Purchase> inputToDomain(List<GeneratePurchaseReportUseCaseInput.PurchaseInput> purchaseInputs, String country) {
        return purchaseInputs.stream()
                .map(input -> Purchase.builder()
                        .product(getProductByName(input.getProductName()))
                        .price(input.getPrice())
                        .quantity(input.getQuantity())
                        .countryEnum(CountryEnum.getByText(country))
                        .build())
                .toList();
    }

    private Product getProductByName(String name) {
        return PRODUCT_LIST.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found product, name = '%s'.".formatted(name)));
    }
}

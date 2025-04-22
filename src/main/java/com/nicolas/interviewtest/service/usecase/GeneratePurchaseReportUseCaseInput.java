package com.nicolas.interviewtest.service.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class GeneratePurchaseReportUseCaseInput {
    private List<PurchaseInput> purchaseInputs;
    private String countryCode;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchaseInput {
        private String productName;
        private double price;
        private int quantity;
    }
}

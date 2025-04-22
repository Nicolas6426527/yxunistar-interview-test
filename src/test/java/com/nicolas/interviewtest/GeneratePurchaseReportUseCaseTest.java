package com.nicolas.interviewtest;

import com.nicolas.interviewtest.entity.enums.CountryEnum;
import com.nicolas.interviewtest.service.usecase.GeneratePurchaseReportUseCase;
import com.nicolas.interviewtest.service.usecase.GeneratePurchaseReportUseCaseInput;
import com.nicolas.interviewtest.service.usecase.GeneratePurchaseReportUseCaseOutput;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GeneratePurchaseReportUseCaseTest {
    GeneratePurchaseReportUseCase useCase = new GeneratePurchaseReportUseCase();

    @Test
    void case1_success() {
        // Arrange
        GeneratePurchaseReportUseCaseInput input = new GeneratePurchaseReportUseCaseInput(
                List.of(
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName("book").price(17.99).quantity(1).build(),
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName("potato chips").price(3.99).quantity(1).build()
                ),
                CountryEnum.CA.getName()
        );
        // Act
        GeneratePurchaseReportUseCaseOutput output = new GeneratePurchaseReportUseCaseOutput();
        useCase.execute(input, output);
        // Assert
        System.out.println(output.getRes());

    }

    @Test
    void case2_success() {
        // Arrange
        GeneratePurchaseReportUseCaseInput input = new GeneratePurchaseReportUseCaseInput(
                List.of(
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName("book").price(17.99).quantity(1).build(),
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName("pencil").price(2.99).quantity(3).build()
                ),
                CountryEnum.NY.getName()
        );
        // Act
        GeneratePurchaseReportUseCaseOutput output = new GeneratePurchaseReportUseCaseOutput();
        useCase.execute(input, output);
        // Assert
        System.out.println(output.getRes());

    }

    @Test
    void case3_success() {
        // Arrange
        GeneratePurchaseReportUseCaseInput input = new GeneratePurchaseReportUseCaseInput(
                List.of(
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName("pencil").price(2.99).quantity(2).build(),
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName("shirt").price(29.99).quantity(1).build()
                ),
                CountryEnum.NY.getName()
        );
        // Act
        GeneratePurchaseReportUseCaseOutput output = new GeneratePurchaseReportUseCaseOutput();
        useCase.execute(input, output);
        // Assert
        System.out.println(output.getRes());
    }
}

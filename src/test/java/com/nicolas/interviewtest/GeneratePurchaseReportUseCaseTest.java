package com.nicolas.interviewtest;

import com.nicolas.interviewtest.entity.enums.CountryEnum;
import com.nicolas.interviewtest.service.usecase.GeneratePurchaseReportUseCase;
import com.nicolas.interviewtest.service.usecase.GeneratePurchaseReportUseCaseInput;
import com.nicolas.interviewtest.service.usecase.GeneratePurchaseReportUseCaseOutput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        String expect =
                """
                        item              price        qty
                        
                        book             $17.99          1
                        potato chips      $3.99          1
                        subtotal:                   $21.98
                        tax:                         $1.80
                        total:                      $23.78""";
        // Act
        GeneratePurchaseReportUseCaseOutput output = new GeneratePurchaseReportUseCaseOutput();
        useCase.execute(input, output);

        // Assert
        assertThat(output.getRes()).isEqualTo(expect);
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
        String expect =
                """
                        item              price        qty
                        
                        book             $17.99          1
                        pencil            $2.99          3
                        subtotal:                   $26.96
                        tax:                         $2.40
                        total:                      $29.36""";

        // Act
        GeneratePurchaseReportUseCaseOutput output = new GeneratePurchaseReportUseCaseOutput();
        useCase.execute(input, output);

        // Assert
        assertThat(output.getRes()).isEqualTo(expect);
    }

    @Test
    void productNotFound_throwIllegalArgumentException() {
        // Arrange
        String productName = "not found";
        GeneratePurchaseReportUseCaseInput input = new GeneratePurchaseReportUseCaseInput(
                List.of(
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName("book").price(17.99).quantity(1).build(),
                        GeneratePurchaseReportUseCaseInput.PurchaseInput.builder().productName(productName).price(2.99).quantity(3).build()
                ),
                CountryEnum.NY.getName()
        );
        String errorMsg = "Not found product, name = '%s'.".formatted(productName);

        // Act + Assert
        assertThatThrownBy(() -> {
            GeneratePurchaseReportUseCaseOutput output = new GeneratePurchaseReportUseCaseOutput();
            useCase.execute(input, output);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMsg);
    }
}

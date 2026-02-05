package com.mirkamolcode.exercise.shipping;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShippingCostCalculatorTest {
    private final ShippingCostCalculator underTest = new ShippingCostCalculator();

    @Test
    void shouldChargeFiveEurosForSmallPackage() {
        assertThat(underTest.calculate(0.5, "Local", false)).isEqualTo(5.00);
    }

    @Test
    void shouldChargeTenEurosForLocalStandardShipping() {
        assertThat(underTest.calculate(2.0, "Local", false)).isEqualTo(10.00);
    }

    @Test
    void shouldChargeTwentyEurosForInternationalShipping() {
        assertThat(underTest.calculate(2.0, "International", false)).isEqualTo(20.00);
    }

    @Test
    void shouldAddExpressFee() {
        assertThat(underTest.calculate(0.5, "International", true)).isEqualTo(20.00);
    }

    @Test
    void shouldThrowExceptionWhenWeightKgIsNegative() {
        assertThatThrownBy(() -> underTest.calculate(-1.0, "Local", true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Negative weight kg can't be shipped");
    }

    @Test
    void shouldThrowExceptionWhenWeightKgIsNull() {
        assertThatThrownBy(() -> underTest.calculate(null, "Local", true))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Weight kg wasn't included");
    }

    @Test
    void shouldThrowExceptionWhenWeightKgIsZero() {
        assertThatThrownBy(() -> underTest.calculate(0.00, "Local", true))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Weight kg wasn't included");
    }

    @ParameterizedTest
    @CsvSource({
            "1.2, International, true, 35",
            "1.2, Local, true, 25",
    })
    void canCalculateAnyKindOfShipping(double wightKg, String region, boolean isExpress, double expected) {
        assertThat(underTest.calculate(wightKg, region, isExpress)).isEqualTo(expected);
    }


}
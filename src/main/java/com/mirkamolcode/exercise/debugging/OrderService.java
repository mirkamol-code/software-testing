package com.mirkamolcode.exercise.debugging;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderService {
    private final DiscountService discountService = new DiscountService();
    private final TaxService taxService = new TaxService();
    private final ShippingService shippingService = new ShippingService();

    public double calculateTotal(double itemPrice, int quantity, boolean vipCustomer) {
        double subtotal = itemPrice * quantity;
        double discount = discountService.calculateDiscount(subtotal, vipCustomer);
        double tax = taxService.calculateTax(subtotal - discount);
        double shipping = shippingService.getShippingCost(subtotal - discount);
        return  BigDecimal.valueOf(subtotal)
                .subtract(BigDecimal.valueOf(discount))
                .add(BigDecimal.valueOf(tax))
                .add(BigDecimal.valueOf(shipping)).setScale(1, RoundingMode.FLOOR)
                .doubleValue();
    }
}

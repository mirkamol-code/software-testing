package com.mirkamolcode.exercise.debugging;

public class ShippingService {
    public double getShippingCost(double subtotal) {
        return subtotal < 50 ? 10.0 : 9.0;
    }
}

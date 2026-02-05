package com.mirkamolcode.exercise.shipping;

public class ShippingCostCalculator {

    public static final int LOCAL_COST = 10;
    public static final int INTERNATIONAL_COST = 20;
    public static final int MINIMUM_SHIPPING_COST = 5;
    public static final int EXPRESS_SHIPPING_COST = 15;

    public double calculate(Double weightKg, String region, boolean express) {
        // TODO: implement using TDD
        var total = 0;
        if (weightKg == null || weightKg == 0.0){
            throw new NullPointerException("Weight kg wasn't included");
        }
        if (weightKg < 0) {
            throw new IllegalArgumentException("Negative weight kg can't be shipped");
        }
        if (weightKg <= 1) {
            total = MINIMUM_SHIPPING_COST;

        }

        if ((weightKg > 1) && (weightKg <= 5) && region.equalsIgnoreCase("Local")) {
            total = LOCAL_COST;

        } else if ((weightKg > 1) && (weightKg <= 5) && region.equalsIgnoreCase("International")) {
            total = INTERNATIONAL_COST;

        }

        return express ? total + EXPRESS_SHIPPING_COST : total;
    }
}

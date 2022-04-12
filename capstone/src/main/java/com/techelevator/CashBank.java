package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;

public class CashBank {
    private static double moneyProvided = 0;

    public static double getMoneyProvided() {
        return moneyProvided;
    }

    public static void addMoney(double inputCurrency){
        moneyProvided += inputCurrency;
    }

    public String getReturnAmount(double cost){
        BigDecimal modifier = new BigDecimal(moneyProvided - cost);
        MathContext thismayaswellhappen = new MathContext(2);
        double change = modifier.doubleValue();
        return NumberFormat.getCurrencyInstance().format(modifier);

    }
}

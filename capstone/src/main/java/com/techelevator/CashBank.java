package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;

public class CashBank {
    private static BigDecimal moneyProvided = new BigDecimal("0.00");
    private static BigDecimal cost = new BigDecimal("0.00");
    private static BigDecimal balance = new BigDecimal("0.00");

    public static String getMoneyProvided() {
        return moneyProvided.toString();
    }

    public static void addMoney(BigDecimal inputCurrency){
        moneyProvided = moneyProvided.add(inputCurrency);
    }



    public BigDecimal getReturnAmount(){
        moneyProvided = moneyProvided.subtract(cost);
        cost = BigDecimal.valueOf(0.00);

        //BigDecimal modifier = new BigDecimal(moneyProvided - cost);
       // MathContext thismayaswellhappen = new MathContext(2);
       // double change = modifier.doubleValue();
        //return NumberFormat.getCurrencyInstance().format(modifier);
        return moneyProvided;

    }
    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}

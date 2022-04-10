package com.techelevator;

public class CashBank {
    private static double moneyProvided = 0;

    public static double getMoneyProvided() {
        return moneyProvided;
    }

    public static void addMoney(double inputCurrency){
        moneyProvided += inputCurrency;
    }
}

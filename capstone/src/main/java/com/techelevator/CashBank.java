package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;

public class CashBank {
    private static final BigDecimal ZERO = new BigDecimal("0.00");
    private static final BigDecimal QUARTER = new BigDecimal("0.25");
    private static final BigDecimal DIME = new BigDecimal("0.10");
    private static final BigDecimal NICKEL = new BigDecimal("0.05");
    private static BigDecimal moneyProvided = new BigDecimal("0.00");
    private static BigDecimal cost = new BigDecimal("0.00");

    public static BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public static void addMoney(BigDecimal inputCurrency){
        moneyProvided = moneyProvided.add(inputCurrency);
    }

    public static void makeChange(){
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        /*
        It will return 0 if x and y are equal, 1 if x is greater than y and -1 if x is smaller than y
         */
        while (moneyProvided.compareTo(ZERO) != 0){
            if (moneyProvided.subtract(QUARTER).compareTo(ZERO) >= 0){
                moneyProvided = moneyProvided.subtract(QUARTER);
                quarters++;
            } else if (moneyProvided.subtract(DIME).compareTo(ZERO) >= 0){
                moneyProvided = moneyProvided.subtract(DIME);
                dimes++;
            } else if (moneyProvided.subtract(NICKEL).compareTo(ZERO) >= 0){
                moneyProvided = moneyProvided.subtract(NICKEL);
                nickels++;
            }
        }
        System.out.println("The machine dispenses: " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels.");
        System.out.println("Have a nice day!");
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
    public void setCost(BigDecimal cost) { this.cost = cost;
    }
}

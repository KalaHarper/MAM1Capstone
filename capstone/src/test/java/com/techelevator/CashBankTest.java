package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CashBankTest extends TestCase {
    CashBank testCashBank = new CashBank();

    @Test
    public void testAddMoney() {
        //Arrange
        BigDecimal expected = new BigDecimal("3.30");
        BigDecimal test = new BigDecimal("1.65");
        //Act
        testCashBank.addMoney(test);
        testCashBank.addMoney(test);
        //Assert
        Assert.assertEquals(expected, testCashBank.getMoneyProvided());
    }

    @Test
    public void testMakeChange(){
        //Expected amount format
        BigDecimal expectedFormat = new BigDecimal("0.00");
        //Amounts added to moneyProvided
        BigDecimal valueOne = new BigDecimal("0.25");
        BigDecimal valueTwo = new BigDecimal("1.85");

        //added the money
        testCashBank.addMoney(valueTwo);
        testCashBank.addMoney(valueOne);

        //calling method in question
        testCashBank.makeChange();

        //Assert
        Assert.assertEquals(expectedFormat, testCashBank.getMoneyProvided());
    }

    public void testGetReturnAmount() {
        //Expected result
        BigDecimal expected = new BigDecimal("2.50");

        //Adding this much
        BigDecimal inputFunds = new BigDecimal("4.00");
        testCashBank.addMoney(inputFunds);

        //Subtracting this much
        BigDecimal cost = new BigDecimal("1.50");

        testCashBank.setCost(cost);

        Assert.assertEquals(expected, testCashBank.getReturnAmount());


    }
}
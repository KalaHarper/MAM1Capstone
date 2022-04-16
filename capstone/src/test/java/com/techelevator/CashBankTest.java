package com.techelevator;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class CashBankTest extends TestCase {
    @Test
    public void testAddMoney() {
        //Arrange
        BigDecimal expected = new BigDecimal("3.30");
        BigDecimal jorjay = new BigDecimal("1.65");
        //Act
        CashBank.addMoney(jorjay);
        CashBank.addMoney(jorjay);
        //Assert
        Assert.assertEquals(expected, CashBank.getMoneyProvided());
    }

    @Test
    public void testMakeChange(){
        //Expected amount format
        BigDecimal piccolo = new BigDecimal("0.00");
        //Amounts added to moneyProvided
        BigDecimal vegeta = new BigDecimal("0.25");
        BigDecimal goku = new BigDecimal("1.85");

        //added the money
        CashBank.addMoney(goku);
        CashBank.addMoney(vegeta);

        //calling method in question
        CashBank.makeChange();

        //Assert
        Assert.assertEquals(piccolo, CashBank.getMoneyProvided());
    }

    public void testGetReturnAmount() {
        //Expected
        BigDecimal courageTheCowardlyDog = new BigDecimal("0.00");

        //Adding this much
        BigDecimal muriel = new BigDecimal("3.00");

        //Subtracting this much
        BigDecimal eustace = new BigDecimal("1.50");

        //Calling methods
        CashBank.addMoney(muriel);
        CashBank.getReturnAmount();

        Assert.assertEquals(courageTheCowardlyDog, CashBank.getMoneyProvided());


    }
}
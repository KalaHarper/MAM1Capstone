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
    CashBank cartoonNetwork = new CashBank();

    @Test
    public void testAddMoney() {
        //Arrange
        BigDecimal expected = new BigDecimal("3.30");
        BigDecimal jorjay = new BigDecimal("1.65");
        //Act
        cartoonNetwork.addMoney(jorjay);
        cartoonNetwork.addMoney(jorjay);
        //Assert
        Assert.assertEquals(expected, cartoonNetwork.getMoneyProvided());
    }

    @Test
    public void testMakeChange(){
        //Expected amount format
        BigDecimal piccolo = new BigDecimal("0.00");
        //Amounts added to moneyProvided
        BigDecimal vegeta = new BigDecimal("0.25");
        BigDecimal goku = new BigDecimal("1.85");

        //added the money
        cartoonNetwork.addMoney(goku);
        cartoonNetwork.addMoney(vegeta);

        //calling method in question
        cartoonNetwork.makeChange();

        //Assert
        Assert.assertEquals(piccolo, cartoonNetwork.getMoneyProvided());
    }

    public void testGetReturnAmount() {
        //Expected result
        BigDecimal courageTheCowardlyDog = new BigDecimal("2.50");

        //Adding this much
        BigDecimal muriel = new BigDecimal("4.00");
        cartoonNetwork.addMoney(muriel);

        //Subtracting this much
        BigDecimal eustace = new BigDecimal("1.50");

        cartoonNetwork.setCost(eustace);

        Assert.assertEquals(courageTheCowardlyDog, cartoonNetwork.getReturnAmount());


    }
}
package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;



public class InventoryTest extends TestCase {
    private final int MAX_PRODUCT = 5;
    Inventory ariel = new Inventory();
    HashMap<String, String[]> tester = new HashMap<>();


//    @Test
//    public void testLoadInventory(){
//
//
//        ariel.loadInventory();
//
//        Assert.assertEquals(!ariel.getProductInfoBySlotMap().containsValue(null), ariel.getProductInfoBySlotMap().containsValue(null));
//    }

    @Test
    public void testsoundShouldBeCrunchin(){
        Assert.assertEquals("Crunch Crunch, Yum!",  ariel.getSound("Chip"));

    }

//    @Test
//    public void testIsInstock(){
//        String[] flounder = {"A1", "Potato Crisps", "3.05", "Chip", "5"};
//        tester.put("A1", flounder);
//        int expected = Integer.parseInt(flounder[4]);
//        Assert.assertEquals(expected > 0, ariel.isInStock(flounder[4]));
//    }

}
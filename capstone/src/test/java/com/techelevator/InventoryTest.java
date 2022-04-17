package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class InventoryTest extends TestCase {

    @Test
    public void testLoadInventory(){
        Inventory ariel = new Inventory();

        ariel.loadInventory();

        Assert.assertEquals(!ariel.getProductInfoBySlotMap().containsValue(null), ariel.getProductInfoBySlotMap().containsValue(null));
    }


    @Test
    public void soundShouldBeGulpin(){

    }


}
package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;



public class InventoryTest extends TestCase {

    Inventory ariel = new Inventory();

    @Test
    public void testSoundShouldBeCrunch(){
        Assert.assertEquals("Crunch Crunch, Yum!",  ariel.getSound("Chip"));

    }


}
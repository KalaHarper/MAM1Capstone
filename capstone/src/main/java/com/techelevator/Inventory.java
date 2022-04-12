package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private static final String MAX_STOCK = "|5"; //Created constant
    private static final String DEFAULT_LOADOUT = "capstone/vendingmachine.csv";
    private static String[] inventoryVariables = new String[5];
    private static List<String[]> inventoryList = new ArrayList<>();
    private static File manifest = new File(DEFAULT_LOADOUT);
    private static HashMap<String, String[]> productInfoBySlot = new HashMap<>(); //Removed old List

    public Inventory() {} //Removed List that was declared in here

    //Made a method to display contents, should improve readability (Anything with an Inventory
    //instance can now simply call "inventory.displayContents")
    public void displayContents(){
        for (String[] item : productInfoBySlot.values()){
            for(String detail : item){
                System.out.print(detail+" ");
            }
            System.out.println();
        }
    }

    public void loadInventory() {
        try (Scanner manifestReader = new Scanner(manifest)) {
            while (manifestReader.hasNextLine()) {
                String lineReader = manifestReader.nextLine() + MAX_STOCK; //Called constant
                inventoryVariables = lineReader.split("\\|");
                productInfoBySlot.put(inventoryVariables[0], inventoryVariables);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Inventory file was not loaded properly. Please check file and run again.");
            System.exit(1); //Changed this (For no real reason)
        }
    }

    public static HashMap<String, String[]> getProductInfoBySlotMap() {
        return productInfoBySlot;
    } //Got rid of getList method, still thinking about if we need this one^^^


    //Changed method name for readability vvv
    public static String getProductValue(String key, int index) {
        String[] placeholder = productInfoBySlot.get(key);
        return placeholder[index];
    }
}
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
    private static List cartList = new ArrayList();
    public static double cost = 0.00;

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
    public static String getProductDetail(String product, int detail){
        String[] placeholder = productInfoBySlot.get(product);
        return placeholder[detail];
    }

    public static boolean isInStock(String product, int detail){
        int temp = Integer.parseInt(Inventory.getProductDetail(product, detail));
        return temp > 0;
    }



    public static void setCartList(List cartList) {
        Inventory.cartList = cartList;
    }
    public static double getCost() {
        return cost;
    }

    public void setCost(String product, int cost) {
        this.cost = Double.parseDouble(getProductDetail(product, cost));
    }

}
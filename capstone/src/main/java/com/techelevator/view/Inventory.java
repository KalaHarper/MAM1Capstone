package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private String slotIdentifier;
    private String productName;
    private String productType;
    private double price;



    private static List<Inventory> inventoryObjects = new ArrayList<>();

    public Inventory(String slotIdentifier, String productName, double price, String productType){
        this.slotIdentifier = slotIdentifier;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    public Inventory(){

    }
    public void Add(String slotIdentifier, String productName, String productType, double price){
        this.slotIdentifier = slotIdentifier;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    public static void inventoryStocker(){
        File vendingInventory = new File("module-1-capstone\\capstone\\vendingmachine.csv");
        try(Scanner hopeThisWorks = new Scanner(vendingInventory)){
            int count = 0;
            while (hopeThisWorks.hasNextLine()){
                count++;
            }
            Inventory[] myInventory = new Inventory[count];
            while (hopeThisWorks.hasNextLine()){
                String nextLine = hopeThisWorks.nextLine();
                String[] george = nextLine.split("\\|");
                double georgeTwin = Double.parseDouble(george[2]);
                inventoryObjects.add(new Inventory(george[0], george[1], georgeTwin, george[3]));

            }
            System.out.println();
        }catch (FileNotFoundException e){
            System.err.println("File doesn't exist");
            System.exit(1);
        }
    }

    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public void setSlotIdentifier(String slotIdentifier) {
        this.slotIdentifier = slotIdentifier;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static List<Inventory> getInventoryObjects() {
        return inventoryObjects;
    }

    public static void setInventoryObjects(List<Inventory> inventoryObjects) {
        Inventory.inventoryObjects = inventoryObjects;
    }

}

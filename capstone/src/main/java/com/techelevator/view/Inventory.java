package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Inventory {
    public Inventory(String slotIdentifier, String productName, String productType, double price){
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

//    public static void inventoryStocker(){
//        File vendingInventory = new File("module-1-capstone\\capstone\\vendingmachine.csv");
//        try(Scanner hopeThisWorks = new Scanner(vendingInventory)){
//            int count = 0;
//            while (hopeThisWorks.hasNextLine()){
//                count++;
//            }
//            Inventory[] myInventory = new Inventory[count];
//            while (hopeThisWorks.hasNextLine()){
//                String nextLine = hopeThisWorks.nextLine();
//                String[] george = nextLine.split("\\|");
//                double georgeTwin = Double.parseDouble(george[2]);
//                for(int i = 0; i < count; i++){
//                    myInventory[i] = new Inventory(george[0], george[1], george[3], georgeTwin);
//                }
//
//            }
//            System.out.println();
//        }catch (FileNotFoundException e){
//            System.err.println("File doesn't exist");
//            System.exit(1);
//        }
//    }

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

    private String slotIdentifier;
    private String productName;
    private String productType;
    private double price;
}

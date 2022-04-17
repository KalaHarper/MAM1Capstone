package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {
    private final List<String> displayIterator = new ArrayList<>();
    private static final Ledger ledger = new Ledger();
    private static final String MAX_STOCK = "|5";
    private static final String DEFAULT_LOADOUT = "capstone/vendingmachine.csv";
    private static final int STOCK_INDEX = 4;
    private static String[] inventoryVariables = new String[5];
    private static final File manifest = new File(DEFAULT_LOADOUT);
    private final HashMap<String, String[]> productInfoBySlot = new HashMap<>();


    public Inventory() {}

    public void displayContents(){
        for (String slotIdentifier : displayIterator){
            for(String detail :  productInfoBySlot.get(slotIdentifier)){
                if (detail.equals("0")){
                    System.out.print("SOLD OUT ");
                    continue;
                }
                if (detail.contains(".")){
                    System.out.print("$"+detail+" ");
                    continue;
                }
                System.out.print(detail+" ");
            }
            System.out.println();
        }
    }

    public void loadInventory() {
        try (Scanner manifestReader = new Scanner(manifest)) {
            while (manifestReader.hasNextLine()) {
                String lineReader = manifestReader.nextLine() + MAX_STOCK;
                inventoryVariables = lineReader.split("\\|");
                displayIterator.add(inventoryVariables[0]);
                productInfoBySlot.put(inventoryVariables[0], inventoryVariables);
                inventoryVariables = null;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Inventory file was not loaded properly. Please check file and run again.");
            ledger.printsToLog("Inventory file was not loaded properly. System shut down to allow proper usage.");
            System.exit(1);
        }
    }

    public String getSound(String info){
        switch (info) {
            case "Chip":
                return "Crunch Crunch, Yum!";
            case "Candy":
                return "Munch Munch, Yum!";
            case "Drink":
                return "Glug Glug, Yum!";
            case "Gum":
                return "Chew Chew, Yum!";
            default:
                return "Dispense message failure";
        }
	}

    public HashMap<String, String[]> getProductInfoBySlotMap() {
        return productInfoBySlot;
    }


    public String getProductCost(String key, int index) {
        String[] placeholder = productInfoBySlot.get(key);
        return placeholder[index];
    }

    public String getProductDetail(String product, int detail){
        String[] placeholder = productInfoBySlot.get(product);
        return placeholder[detail];
    }

    public boolean isInStock(String product){
        int temp = Integer.parseInt(getProductDetail(product, STOCK_INDEX));
        return temp > 0;
        /*
        alternate return value:
        return !Inventory.getProductDetail(product, STOCK_INDEX).equals("0");
        */
    }

    public void decrementStock(String slotIndicator){
        inventoryVariables = productInfoBySlot.get(slotIndicator.toUpperCase(Locale.ROOT));
        int amountInStock = Integer.parseInt(getProductDetail(slotIndicator, STOCK_INDEX));
        amountInStock --;
        inventoryVariables[STOCK_INDEX] = String.valueOf(amountInStock);
        productInfoBySlot.replace(slotIndicator, inventoryVariables);
        inventoryVariables = null;
    }

    public List getDisplayIterator(){
        return displayIterator;
    }
}
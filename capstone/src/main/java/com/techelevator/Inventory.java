package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {
    private static final String MAX_STOCK = "|5"; //Created constant
    private static final String DEFAULT_LOADOUT = "capstone/vendingmachine.csv";
    private static final int STOCK_INDEX = 4;
    private static String[] inventoryVariables = new String[5];
    private static List<String[]> inventoryList = new ArrayList<>();
    private static File manifest = new File(DEFAULT_LOADOUT);
    private static HashMap<String, String[]> productInfoBySlot = new HashMap<>(); //Removed old List
    private static List cartList = new ArrayList();


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
                inventoryVariables = null;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Inventory file was not loaded properly. Please check file and run again.");
            System.exit(1); //Changed this (For no real reason)
        }
    }

    public String getSound(String info){
        if(info.equals("Chip")){
			return "Crunch Crunch, Yum!";
		}else if(info.equals("Candy")){
			return "Munch Munch, Yum!";
		}else if(info.equals("Drink")){
			return "Glug Glug, Yum!";
		}else if(info.equals("Gum")){
			return "Chew Chew, Yum!";
		}else {
			return "Dispense message failure";
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

    public static void deincrementStock(String slotIndicator){
        inventoryVariables = productInfoBySlot.get(slotIndicator.toUpperCase(Locale.ROOT));
        int amountInStock = Integer.parseInt(getProductDetail(slotIndicator, STOCK_INDEX));
        amountInStock --;
        inventoryVariables[STOCK_INDEX] = String.valueOf(amountInStock);
        productInfoBySlot.replace(slotIndicator, inventoryVariables);
       inventoryVariables = null;
    }

    public static void setCartList(List cartList) {
        Inventory.cartList = cartList;
    }




}
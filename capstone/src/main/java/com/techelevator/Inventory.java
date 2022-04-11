package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private static final String DEFAULT_LOADOUT = "capstone/vendingmachine.csv";
    private static String[] inventoryVariables = new String[5];
   //TODO: refactor list into hashmap
    private static List<String[]> inventoryList = new ArrayList<>();
    private static File manifest = new File(DEFAULT_LOADOUT);
    // private static HashMap<String, String> slotIndicatorHashMap = new HashMap<>();
    private static HashMap<String, String[]> testerMap = new HashMap<>();

    public Inventory() {
//        List<String[]> inventoryList = Inventory.inventoryList;

    }

    public void loadInventory() {
        try (Scanner manifestReader = new Scanner(manifest)) {
            while (manifestReader.hasNextLine()) {
                String lineReader = manifestReader.nextLine() + "|5";
                inventoryVariables = lineReader.split("\\|");
                testerMap.put(inventoryVariables[0], inventoryVariables);
                //slotIndicatorHashMap.put(inventoryVariables[0], inventoryVariables[1]);
                inventoryList.add(inventoryVariables);
            }
        } catch (FileNotFoundException e) {
            System.err.println("No file found at that location");

        }
    }

    //    public static HashMap<String, String> getSlotIndicatorHashMap() {
//        return slotIndicatorHashMap;
//    }
    public static HashMap<String, String[]> getTesterMap() {
        return testerMap;
    }

    public static List<String[]> getInventoryList() {
        return inventoryList;
    }

    public static String selectingElementFromTester(String key, int index) {
        String[] placeholder = testerMap.get(key);
        return placeholder[index];
    }
}
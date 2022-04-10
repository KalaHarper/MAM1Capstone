package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private static final String DEFAULT_LOADOUT = "capstone/vendingmachine.csv";
    private static String[] inventoryVariables = new String[5];
    private static List<String[]> inventoryList = new ArrayList<>();
    private static File manifest = new File(DEFAULT_LOADOUT);

    public Inventory(){
    }

    public void loadInventory(){
        try (Scanner manifestReader = new Scanner(manifest)){
            while(manifestReader.hasNextLine()){
                String lineReader = manifestReader.nextLine() + "|5";
                inventoryVariables = lineReader.split("\\|");
                inventoryList.add(inventoryVariables);
            }
        }catch (FileNotFoundException e){
            System.err.println("No file found at that location");

        }
    }
}

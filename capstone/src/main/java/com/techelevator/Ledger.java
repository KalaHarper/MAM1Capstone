package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class Ledger {
    private static final HashMap<String, Integer> sales = new HashMap<>();
    private static final HashMap<String, String[]> temp = Inventory.getProductInfoBySlot();

    public void startSalesReport(){
        for (String[] item : temp.values()){
            sales.put(item[1], 0);
        }
    }

    public void printsToLog(String info){
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("capstone\\Log.txt"), true))){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:s");
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            writer.println(simpleDate.format(calendar.getTime()) + " " + info);
            // how do I make this so the price is reformatted? Should I make an instance of number format somewhere else?
        }   catch(FileNotFoundException e){
                System.err.println("No log text file loaded.");
                System.exit(1);
        }
    }

    public void updateSalesReport(String itemName){
        int currentSales = sales.get(itemName);
        sales.replace(itemName, currentSales++);
    }

    public void printSalesReport(){
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("capstone\\SalesReport.txt"), true))){
            writer.println();
            writer.println();
            writer.println();
            for (String name : sales.keySet()){
                writer.println(name + " | " + sales.get(name));
            }

        } catch (FileNotFoundException e){
            System.err.println("There is nothing here for you to find, mortal");
            System.exit(1);
        }
    }
}

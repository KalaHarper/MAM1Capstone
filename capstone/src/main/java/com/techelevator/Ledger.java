package com.techelevator;

import javax.management.ObjectName;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;


public class Ledger {
    private static final HashMap<String, Integer> sales = new HashMap<>();

    public void printsToLog(String info) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("capstone\\Log.txt"), true))) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            writer.println(simpleDate.format(calendar.getTime()) + " " + info);
            // how do I make this so the price is reformatted? Should I make an instance of number format somewhere else?
        } catch (FileNotFoundException e) {
            System.err.println("No log text file loaded.");
            System.exit(1);
        }
    }

    public void startSalesReport() {
        //considered possiblity of making source file interchangeable
        File salesReport = new File("capstone\\SalesReport.txt");
        try (Scanner salesReader = new Scanner(salesReport)) {
            while (salesReader.hasNextLine()) {
                String detail = salesReader.nextLine();
                String[] line = detail.split("\\|");
                sales.put(line[0].trim(), Integer.valueOf(line[1].trim()));
            }

        } catch (FileNotFoundException e) {
            System.err.println("No sales report file could be loaded");
            System.exit(1);
        }
    }

    public void updateSalesReport(String itemName) {
        int currentSales = sales.get(itemName);
        sales.merge(itemName, 1, Integer::sum);
        try (PrintWriter writer = new PrintWriter("capstone\\SalesReport.txt")) {
            for (String name : sales.keySet()) {
                writer.println(name + " | " + sales.get(name));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Overwriting the sales report did not go well ='(");
        }
    }


    public void printSalesReport() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDate = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss_aa");
            String fileName = "capstone\\" + simpleDate.format(calendar.getTime()) + ".txt";
        try(PrintWriter writer = new PrintWriter(fileName)){
                for (String name : sales.keySet()) {
                    writer.println(name + " | " + sales.get(name));
                }
            }catch (FileNotFoundException e){
                System.out.println("printing report went repoorly");
            }
        System.out.println(fileName);
    }
}


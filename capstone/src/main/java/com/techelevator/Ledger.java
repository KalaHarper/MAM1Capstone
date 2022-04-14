package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;


public class Ledger {

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
}

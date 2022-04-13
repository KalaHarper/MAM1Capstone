package com.techelevator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;


public class Ledger {


    public void printsToLog(String info){
        try( PrintWriter writer = new PrintWriter("capstone\\log.txt")){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:s");
            writer.println(simpleDate.format(calendar.getTime()) + " " + info);
        }   catch(FileNotFoundException e){
                System.err.println("No log text file loaded.");
                System.exit(1);
        }
    }
}

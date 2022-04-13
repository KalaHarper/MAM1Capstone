package com.techelevator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Ledger {


    public void printsToLog(String info){
        try( PrintWriter writer = new PrintWriter("capstone\\log.txt")){
           LocalDate date = LocalDate.now();
           LocalTime time = LocalTime.now();
            DateTimeFormatter dateTimeFormatted = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            writer.append(date + " " + time + " " + info);
            writer.flush();
        }   catch(FileNotFoundException e){
                System.err.println("No log text file loaded.");
                System.exit(1);
        }
    }
}

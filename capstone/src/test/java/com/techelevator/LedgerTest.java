package com.techelevator;
import junit.framework.TestCase;
import org.junit.Before;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LedgerTest extends TestCase {

    @Before

    public void write(Writer writer, String info) throws IOException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        writer.write(String.valueOf(simpleDate.format(calendar.getTime()) + " " + info));
    }

    public void testPrintsToLogBodyFunctionality() throws IOException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String informationToBePrinted = "\n This message should appear after the date and time in \"MM/dd/yyyy hh:mm:ss aa\" format";
        StringWriter stringWriter = new StringWriter();


        write(stringWriter, informationToBePrinted);
        assertEquals(simpleDate.format(calendar.getTime()) + " " + informationToBePrinted, stringWriter.toString());
    }


}

package com.techelevator.view;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class LogWriter {
    public static void writeToLog(String logEntry) throws IOException {
        try (FileWriter writer = new FileWriter("Log.txt", true)) {
            writer.write(logEntry);
            writer.write(System.lineSeparator()); // Add a newline
        }
    }
    public static String logEntry(String action, double amount, double newBalance) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timestamp = dateFormat.format(new Date());
        return String.format("%s %s: $%.2f $%.2f", timestamp, action, amount, newBalance);
        //returns as string, string, $float, $float
    }
    public static String logEntryProduct(String productName, String productID, double itemPrice, double newBalance) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timestamp = dateFormat.format(new Date());
        return String.format("%s %s %s $%.2f $%.2f", timestamp, productName, productID, itemPrice, newBalance);
        //returns time stamp, product name, productID, Price, Remaining balance of items left in the machine

    }
    public static String logEntryChange(String action, double money, double change) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timestamp = dateFormat.format(new Date());
        return String.format("%s %s: $%.2f $%.2f", timestamp, action, money, change);
    } //returns time stamp, action that was carried out, adding/subtracting money, Remaining balance of money leftover after the transaction

}
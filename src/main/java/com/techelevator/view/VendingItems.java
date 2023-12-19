package com.techelevator.view;
import com.techelevator.VendingMachineCLI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class VendingItems {
    private final String itemSlot;
    private final String itemName;
    private final double itemPrice;
    private final String itemCategory;
    private final String vendingMachineFile;
    public VendingItems(String itemSlot, String itemName, double itemPrice, String itemCategory) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.vendingMachineFile = "vendingmachine.csv";
    }
    public String getItemSlot() {
        return itemSlot;
    }
    public String getItemName() {
        return itemName;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public String getItemCategory() {
        return itemCategory;
    }
    public static Map<String, VendingItems> VendingMachineFile(String vendingMachineFile) {
        Map<String, VendingItems> itemsMap = new HashMap<>();// Created a map to hold items
        try (Scanner scanner = new Scanner(new File(vendingMachineFile))) { // Created a scanner to read the vending machine file
            while (scanner.hasNextLine()) {// keeps the file open until the last line is read
                String line = scanner.nextLine(); // Stores the contains of each  in the line variable
                String[] parts = line.split("\\|");// split each line into different parts
                if (parts.length == 4) {
                    String itemSlot = parts[0];// Gets the slot number from file
                    String itemName = parts[1];// Gets the name of item
                    double itemPrice = Double.parseDouble(parts[2]);// Gets the price of Item and turns it to a double
                    String itemCategory = parts[3];// Gets the type of item from file (Drink, Candy... ect.)
                    VendingItems vendingItem = new VendingItems(itemSlot, itemName, itemPrice, itemCategory);// add itemSlot, itemName, itemPrice, itemCategory to values of map
                    itemsMap.put(itemSlot, vendingItem);// Puts the item slot as keys and vending items as value
                }
            }
        } catch (FileNotFoundException e) { //catches if the file not found
            throw new RuntimeException(e);
        }
        return itemsMap;// allows items to be used in another class
    }
}
package com.techelevator.view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class PurchaseMenu {
    private static final int QUARTER_VALUE = 25;
    private static final int DIME_VAlUE = 10;
    private static final int NICKEL_VALUE = 5;
    int amountOfItems = 5;
    Scanner userInput = new Scanner(System.in);
    public double feedMoney(double money) {
        System.out.println("Feed Money");
        try {
            String userInputString = userInput.nextLine().trim(); // Read a line of input and trim leading/trailing spaces
            if (userInputString.isEmpty()) {
                System.out.println("Please enter a proper money value");
                return money; // Return the current 'money' value without making any changes.
            }
            double moneyToAdd = Double.parseDouble(userInputString); // Parse the input as a double
            if (moneyToAdd > 0) {
                money += moneyToAdd;
                System.out.println("Current Money Provided: $" + String.format("%.2f", money));
                System.out.println(" ");
                String logEntry = LogWriter.logEntry("FEED MONEY", moneyToAdd, money);
                try {
                    LogWriter.writeToLog(logEntry);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Please enter a positive value.");
            }
        } catch (NumberFormatException e) { // Catch NumberFormatException for invalid double input
            System.out.println("Please enter a proper money value");
            userInput.nextLine(); // Consume the invalid input
        } catch (NoSuchElementException e) {
            System.out.println("Please enter a proper money value");
        }
        return money;
    }
    public double selectProduct(double money) {
        displayItems();
        Map<String, VendingItems> itemsMap = VendingItems.VendingMachineFile("vendingmachine.csv");//Map to store VendingItems
        // Scanner itemSlotInput = new Scanner(System.in); //Scanner for User Input
        System.out.print("Enter Item Slot: ");
        String selectedSlot = userInput.nextLine();
        if (!itemsMap.containsKey(selectedSlot.toUpperCase())) {//Check if input is invalid
            System.out.println("Invalid Item Slot Please Try Again");
        }
        if (itemsMap.containsKey(selectedSlot.toUpperCase())) { //Check if input is valid
            VendingItems selectedItem = itemsMap.get(selectedSlot.toUpperCase());
            if (money > selectedItem.getItemPrice() && amountOfItems > 0) { //Check if user has money and there are items left in machine
                amountOfItems -= 1; //reduce amountOfItems by 1 once user purchases an item
                money -= selectedItem.getItemPrice(); //subtract money from the price of the item
                System.out.println("Amount of " + selectedItem.getItemName() + " left in machine: " + amountOfItems);
                String logEntryProduct = LogWriter.logEntryProduct(selectedItem.getItemName(), selectedItem.getItemSlot(), selectedItem.getItemPrice(), money);
                //Log the purchase into Log.txt file
                try {
                    LogWriter.writeToLog(logEntryProduct);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                printSound(selectedItem);
            } else if (amountOfItems <= 0) {
                System.out.println("SOLD OUT"); //if there are no more of that item in the machine print SOLD OUT
            } else if (money < selectedItem.getItemPrice()) {//User doesn't have enough money for selected item
                System.out.println("Insufficient Funds");
            }
        }
        return money;
    }
    private void printSound(VendingItems selectedItem) {
        if (selectedItem.getItemCategory().contains("Chip")) { //display messages based on the item category
            System.out.println("Crunch Crunch, Yum!");
        }
        if (selectedItem.getItemCategory().contains("Drink")) {
            System.out.println("Glug Glug, Yum!");
        }
        if (selectedItem.getItemCategory().contains("Candy")) {
            System.out.println("Munch Munch, Yum!");
        }
        if (selectedItem.getItemCategory().contains("Gum")) {
            System.out.println("Chew Chew, Yum!");
        }
    }
    public double getchange(double money) {
        if (money > 0) {
            int changeInCents = (int) (money * 100);// Turns money in to an integer then multiplies it by 100. To make divisible by the int final values we created
            System.out.println("Your change is: $" + String.format("%.2f", (double) changeInCents / 100));// Prints to user what the change is
            int quarters = changeInCents / QUARTER_VALUE; //updates change value to show how many Quarters
            changeInCents %= QUARTER_VALUE;// checks how many quarters can come out of money value
            int dimes = changeInCents / DIME_VAlUE;//updates change value to show how many Dimes
            changeInCents %= DIME_VAlUE;//checks how many dimes can come out of remaining money value
            int nickels = changeInCents / NICKEL_VALUE;//updates change value to show how many Nickels
            changeInCents %= NICKEL_VALUE;// checks how many nickels can come out of remaining money value
            System.out.println("Quarters: " + quarters);
            System.out.println("Dimes: " + dimes);
            System.out.println("Nickels: " + nickels);
            //return money;
        } else {
            money = 0;
        }
        return money;
    }
    public double finishTransaction(double money) {
        getchange(money);
        int changeInCents = (int) (money * 100);// Turns money in to an integer then multiplies it by 100. To make divisible by the int final values we created
        if (changeInCents <= 0) { // checks to see if balance remain is 0
            System.out.println("No change to be Given. Thank you for using our vending machine.");
        } else {
            System.out.println("Thank you for using our vending machine");
        }
        String logEntryChange = LogWriter.logEntryChange("GIVE CHANGE", money, changeInCents);// updates the logWriter
        try {
            LogWriter.writeToLog(logEntryChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(1);// close the program after finish transaction is called
        return money;
    }
    public void displayItems() {
        File vendingData = new File("vendingmachine.csv");// Add files to be read
        try (Scanner fileReader = new Scanner(new File(String.valueOf(vendingData)))) { // is the file Reader object
            while (fileReader.hasNextLine()) {  // cycles through each line of file
                String lineInput = fileReader.nextLine(); //  add  each line of file to varible lineInput
                System.out.println(lineInput + "|" + amountOfItems);// print that line
            }
        } catch (FileNotFoundException e) { // if the file is not found
            System.out.println("There looks to be a problem " + e.getMessage());
        }
    }
}


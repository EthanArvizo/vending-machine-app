package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DisplayMenu extends VendingMachineCLI {
    public DisplayMenu(Menu menu) {
        super(menu);
    }
    int amountOfItems = 5;
    //double money = 0.0;
    public void displayItems(){
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

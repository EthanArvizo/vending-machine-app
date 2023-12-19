package com.techelevator.view;
import com.techelevator.VendingMachineCLI;

import java.io.IOException;

public class FinishTransaction extends VendingMachineCLI {
    public FinishTransaction(Menu menu) {
        super(menu);
    }

    private static final int QUARTER_VALUE = 25;
    private static final int DIME_VAlUE = 10;
    private static final int NICKEL_VALUE = 5;


    public void processTransaction(double money) {
        int changeInCents = (int) (money * 100);
        if(changeInCents <=0){
            System.out.println("No change to be Given. Thank you for using our vending machine.");
            return;
        }
        System.out.println("Your change is: $" + String.format("%.2f", (double) changeInCents / 100));
        int quarters = changeInCents/ QUARTER_VALUE;
        changeInCents %= QUARTER_VALUE;

        int dimes =  changeInCents/DIME_VAlUE;
        changeInCents %= DIME_VAlUE;

        int nickels = changeInCents/NICKEL_VALUE;
        changeInCents %= NICKEL_VALUE;

        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        // this.money=0.0;
        System.out.println("Thank you for using our vending machine");

        String logEntryChange = LogWriter.logEntryChange("GIVE CHANGE",money,changeInCents);
        try {
            LogWriter.writeToLog(logEntryChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(1);
    }


}
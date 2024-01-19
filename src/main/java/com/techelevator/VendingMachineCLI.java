package com.techelevator;


import com.techelevator.view.ItemList;
import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseOptions;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String FEED_MONEY = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY,SELECT_PRODUCT,FINISH_TRANSACTION};
	private final PurchaseOptions purchaseOptions;
	private final Menu menu;
	private double currentBalance = 0.00;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		purchaseOptions = new PurchaseOptions();
	}
	public void run() {
		Scanner scanner = new Scanner (System.in);


		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				purchaseOptions.displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseOptions.displayBalance(currentBalance);
				while (true) {
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchaseChoice.equals(FEED_MONEY)) {
						purchaseOptions.displayBalance(currentBalance);
						currentBalance = purchaseOptions.feedMoney(currentBalance);
					} else if (purchaseChoice.equals(SELECT_PRODUCT)) {
						purchaseOptions.displayItems();

						currentBalance = Double.parseDouble(purchaseOptions.selectProduct(purchaseOptions.displayItems(),currentBalance));
					} else if (purchaseChoice.equals(FINISH_TRANSACTION)) {
						purchaseOptions.finishTransaction();
						System.exit(0);
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you for using this vending machine!");
				System.out.println("Have a great day.");
				break;
			}
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
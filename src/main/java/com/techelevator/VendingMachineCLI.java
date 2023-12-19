package com.techelevator;
import com.techelevator.view.*;

//import java.lang.System.exit;
import java.util.Scanner;
public class VendingMachineCLI {
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private final Menu menu;
    private static final String PURCHASE_MENU_OPTION_FINISHTRANS = "Finish Transaction";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISHTRANS};
    protected double money = 0.0;
    PurchaseMenu purchaseMenu;
    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
        purchaseMenu = new PurchaseMenu();
    }
    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                purchaseMenu.displayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                while (true) {
                    String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        double moneyToAdd = purchaseMenu.feedMoney(money);
                        money = moneyToAdd;
                    } else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        money = purchaseMenu.selectProduct(money);
                    } else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISHTRANS)) {
                        PurchaseMenu transaction = new PurchaseMenu();
                        transaction.finishTransaction(money);
                    } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                        break;
                    }
                }
            }
        }
    }
    public double getMoney() {
        return money;
    }
    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
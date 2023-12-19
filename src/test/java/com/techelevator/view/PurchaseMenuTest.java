package com.techelevator.view;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
class PurchaseMenuTest {
    @Test
    public void  feedMoneyTest() {
        String userInput = "10";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double expectedResult = 10;//arrange
        double actualResult = purchaseMenu.feedMoney(0);
        Assert.assertEquals(expectedResult, actualResult, 0.01);
    }
    @Test
    public void feedMoneyTest20() {
        String userInput = "-20";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double actualResult = purchaseMenu.feedMoney(0);
        double expectedResult = 0;
        Assert.assertEquals(expectedResult, actualResult, 0.01);
    }
    @Test
    public void feedMoneyTestEmptyString(){
        String userInput = "";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double actualResult = purchaseMenu.feedMoney(0);
        double expectedResult = 0;
        Assert.assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    public void selectProductValid() {
        double initialMoney = 5.0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String userInput = "D4\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double updatedMoney = purchaseMenu.selectProduct(initialMoney);
        assertEquals(4.25, updatedMoney, 0.01);
    }
    @Test
    public void selectProductValidMissMatch() {
        double initialMoney = 5.0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String userInput = "H4\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double updatedMoney = purchaseMenu.selectProduct(initialMoney);
        assertEquals(5.00, updatedMoney, 0.01);
    }
    @Test
    public void selectProductValidEmpty() {
        double initialMoney = 5.0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String userInput = "\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double updatedMoney = purchaseMenu.selectProduct(initialMoney);
        assertEquals(5.00, updatedMoney, 0.01);
    }
    @Test
    public void selectProductValidSoldOut() {
        String userInput = "D4\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        purchaseMenu.amountOfItems = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        double updatedMoney = purchaseMenu.selectProduct(5.0);
        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("SOLD OUT"));
        assertEquals(5.0, updatedMoney, 0.01);
    }
    @Test
    public void getChangeNoChange() {
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double money = 0;
        double actualResult = purchaseMenu.getchange(money);
        double expectedResult = 0.0;
        Assert.assertEquals(expectedResult, actualResult, 0.01);
    }
    @Test
    public void getChangeChange() {
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double money = 2.5;
        double actualResult = purchaseMenu.getchange(money);
        double expectedResult = 2.5;
        assertEquals(expectedResult, actualResult, 0.01);
    }
    @Test
    public void getChangeNegativeChange() {
        PurchaseMenu purchaseMenu = new PurchaseMenu();
        double money = -2.5;
        double actualResult = purchaseMenu.getchange(money);
        double expectedResult = 0;
        assertEquals(expectedResult, actualResult, 0.01);
    }
}
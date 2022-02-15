package com.jano;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean mainLoop = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        while (mainLoop) {
            boolean flag = true;
            printMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("\nYour card has been created");
                    System.out.println("Your card number:");
                    String cardNumber = generateCardNumber();
                    System.out.println(cardNumber);
                    System.out.println("Your card PIN:");
                    String pin = generatePIN();
                    System.out.println(pin + "\n");
                    Account acc = new Account(cardNumber, pin);
                    accounts.add(acc);
                    break;
                case "2":
                    boolean logLoop = true;
                    System.out.println("\nEnter your card number:");
                    String cardNumberIn = scanner.nextLine();
                    System.out.println("Enter your PIN:");
                    String pinIn = scanner.nextLine();
                    for (Account account : accounts) {
                        if (account.getCardNumber().equals(cardNumberIn) && account.getPIN().equals(pinIn)) {
                            System.out.println("\nYou have successfully logged in!\n");
                            flag = false;
                            while (logLoop) {
                                printLogMenu();
                                String logInput = scanner.nextLine();
                                switch (logInput) {
                                    case "1":
                                        System.out.println("\nBalance: 0\n");
                                        break;
                                    case "2":
                                        System.out.println("\nYou have successfully logged out!\n");
                                        logLoop = false;
                                        break;
                                    case "0":
                                        logLoop = false;
                                        break;

                                }
                            }
                        }
                    }
                    if (flag) {
                        System.out.println("\nWrong card number or PIN!\n");
                    }
                    break;
                case "0":
                    System.out.println("\nBye!");
                    mainLoop = false;
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("1. Create an account\n" + "2. Log into account\n" + "0. Exit");
    }

    public static void printLogMenu() {
        System.out.println("1. Balance\n" + "2. Log out\n" + "0. Exit");
    }

    public static String generateCardNumber() {
        String cardNumber = null;
        boolean flag = true;
        while (flag) {
            String BIN = "400000";
            long num = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            cardNumber = BIN + num;
            if (validateCreditCardNumber(cardNumber)) {
                flag = false;
            }
        }
        return cardNumber;
    }

    public static String generatePIN() {
        Random random = new Random();
        int num = random.nextInt(9999);
        return String.format("%04d", num);
    }

    public static boolean validateCreditCardNumber(String cardNumber) {
        int[] creditCardInt = new int[cardNumber.length()];

        for (int i = 0; i < cardNumber.length(); i++) {
            creditCardInt[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }

        for (int i = creditCardInt.length - 2; i >= 0; i = i - 2) {
            int temp = creditCardInt[i];
            temp *= 2;
            if (temp > 9) {
                temp = temp % 10 + 1;
            }
            creditCardInt[i] = temp;
        }

        int total = 0;

        for (int j : creditCardInt) {
            total += j;
        }

        return total % 10 == 0;
    }
}

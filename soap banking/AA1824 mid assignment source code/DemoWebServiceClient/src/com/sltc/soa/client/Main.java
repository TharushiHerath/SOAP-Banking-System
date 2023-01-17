package com.sltc.soa.client;

import com.sltc.soa.client.stub.DemoWS;
import com.sltc.soa.client.stub.DemoWSService;

import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
          //  URL url = new URL("http://demowebservice.com:8080/currencyservice?wsdl");
          //    DemoWSService demoWSService = new DemoWSService(url);

        DemoWSService demoWSService = new DemoWSService();
        DemoWS demoWSPort = demoWSService.getDemoWSPort();
        Scanner scan = new Scanner(System.in);
        if (demoWSPort.addUser("007301", 1500)) {
            System.out.println("First Account create successfully");
        }
        if (demoWSPort.addUser("004892", 10200)) {
            System.out.println("Second Account create successfully");
        }
        if (demoWSPort.addUser("005812", 2050)) {
            System.out.println("Third Account create successfully");
        }
        if (demoWSPort.addUser("001457", 15000)) {
            System.out.println("Fourth Account create successfully");
        }
        if (demoWSPort.addUser("002308", 50000)) {
            System.out.println("Fifth Account create successfully");
        }

//money deposit
        System.out.println("\n");
        System.out.println("Enter your user account number :");
        String userName = scan.nextLine();
        System.out.println("\n");
        System.out.println("Enter the amount you want to deposit: ");
        String str = scan.nextLine();
        float currBalance = demoWSPort.deposit(Float.parseFloat(str), userName);
        System.out.println("\n");
        System.out.println("Updated balance : Rs:" + currBalance + "/=");

        //money withdraw
        System.out.println("\n");
        System.out.println("**************************************************************************");
        System.out.println("Enter the amount you want to withdraw: ");
        str = scan.nextLine();
        currBalance = demoWSPort.withdraw(Float.parseFloat(str), userName);
        if (currBalance != -1) {
            System.out.println("\n");
            System.out.println("Updated balance : Rs:" + currBalance + "/=");
        } else {
            System.out.println("insufficient Balance");
            System.out.println("please try again:(");
        }

//money transfer
        System.out.println("\n");
        System.out.println("**************************************************************************");
        System.out.println("Enter the amount you want to transfer: ");
        str = scan.nextLine();
        System.out.println("\n");
        System.out.println("Enter an account number to transfer money: ");
        String accName = scan.nextLine();
        currBalance = demoWSPort.transfer(Float.parseFloat(str), userName, accName);
        if (currBalance != -1) {
            System.out.println("\n");
            System.out.println("Updated balance: " + currBalance + "/=");
        } else {
            System.out.println("Insufficient balance");
            System.out.println("please try again:(");
        }

    }



        private static int readInputInt ()
        {
            int inputInt = 0;
            boolean numberFound = false;
            Scanner scan = new Scanner(System.in);
            do {
                System.out.println("Please input an integer");
                String inputStr = scan.next();
                try {
                    inputInt = Integer.parseInt(inputStr);
                    numberFound = true;
                } catch (Exception e) {
                    System.out.println("Invalid input " + inputStr + ". Please input a number.");
                }

            } while (!numberFound);
            return inputInt;
        }
    }


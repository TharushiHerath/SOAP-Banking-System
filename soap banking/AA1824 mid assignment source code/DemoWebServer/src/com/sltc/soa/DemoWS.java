package com.sltc.soa;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.util.HashMap;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class
DemoWS
{
    HashMap<String,Float> UserAccounts = new HashMap<String,Float>();
    //String ConnectedUserAccount = "";

    @WebMethod
    public boolean addUser(String userAccount, float bank_balance){
        System.out.println("\n");
        System.out.println( "your Account Creation is successfully complete :)");
        System.out.println(" This is your current balance " + bank_balance);
        UserAccounts.put(userAccount, bank_balance);
        return true;
    }

    //money deposit
    @WebMethod
    public float deposit(float amount, String accountNumber){
        float balance = UserAccounts.get(accountNumber);
        balance += amount;
        UserAccounts.put(accountNumber, balance);

        System.out.println("\n");
        System.out.println( "Your money deposit process is done");
        System.out.println("\n");
        System.out.println("This is your current balance " + balance);

        return balance;
    }

    //money withdraw
    @WebMethod
    public float withdraw(float amount, String accountNumber){
        float balance = UserAccounts.get(accountNumber);
        if(balance >= amount){
            balance -= amount;
            UserAccounts.put(accountNumber, balance);
            System.out.println( "Your money withdraw process is done");
            System.out.println("\n");
            System.out.println("This is your current balance " + balance);
            return balance;
        }else{
            System.out.println("*************************************************************************");
            System.out.println("cannot continues:( please check your  Balance");
            return -1;
        }
    }
// money transfer
    @WebMethod
    public float transfer(float amount, String myAccountName, String accountNumber){
        float balance = UserAccounts.get(myAccountName);
        if(balance >= amount){
            balance -= amount;
            UserAccounts.put(myAccountName, balance);
            UserAccounts.put(accountNumber,UserAccounts.get(accountNumber)+amount);
            System.out.println( "your money Transfer process is done");
            System.out.println("\n");
            System.out.println("This is your current balance " + balance);
            return balance;
        }else{
            System.out.println("*************************************************************************");
            System.out.println("cannot continues:( please check your  Balance");
            return -1;
        }
    }

    public static void main(String[] args){
        Endpoint.publish("http://localhost:8888/DemoWebService", new DemoWS());
        System.out.println("Bank server starts");
    }
}

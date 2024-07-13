package com.revature.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.revature.exception.LoginFail;
import com.revature.entity.Account;
import com.revature.service.AccountService;

public class AccountController {
    
    private Scanner scanner;
    private AccountService service;

    public AccountController(Scanner scanner, AccountService service){
        this.scanner = scanner;
        this.service = service;
    }

    public void promptUserForAccountService(Map<String,String> controlMap){
        System.out.printf("Welcome to the First World Bank of Faerun! What would you like to do, %s?\n", controlMap.get("User"));
        System.out.println("1. create checking account");
        System.out.println("2. get balance of checking account");
        System.out.println("3. withdraw from checking account");
        System.out.println("4. deposit into checking account");
        System.out.println("q. log out");
        try{
            String userActionIndicated = scanner.nextLine();
            switch (userActionIndicated) {
                case "1":
                    System.out.println("Checking account creations will go here, current User ID: " + controlMap.get("User ID"));
                    createNewCheckingAccount(Integer.parseInt(controlMap.get("User ID")));
                    break;
                case "2":
                    System.out.println("Checking account balance will go here");
                    getAllAccountsByUserID(Integer.parseInt(controlMap.get("User ID")));
                    break;
                case "3":
                    System.out.println("Prompts for withdrawl will be placed here");
                    withdrawPrompt(Integer.parseInt(controlMap.get("User ID")));
                    break;
                case "4":
                    System.out.println("Prompts for deposit will be placed here");
                    depositPrompt(Integer.parseInt(controlMap.get("User ID")));
                    break;
                case "5":
                    System.out.println("Prompts for closing an account will be placed here");
                    break;
                case "q":
                    System.out.printf("Goodbye, %s!\n", controlMap.get("User"));
                    controlMap.put("User", "No one logged in");
                    controlMap.put("User ID", Integer.toString(0));
                    controlMap.put("Logged In", "false");
            }
        } catch(LoginFail exception){
            System.out.println("New custom exception messages will be here!");
        }
    }

    public void createNewCheckingAccount(int id){
        Account newAccount = new Account(id, 0.0f, 0.0f, 0.0f);
        //System.out.println(newAccount);
        Account acc = service.createNewCheckingAccount(newAccount);
        System.out.printf("New checking account created: %s \n", acc);
    }

    public void createNewSavingAccount(int id){
        Account newAccount = new Account(id, 0.0f, 0.0f, 0.0f);
        Account acc = service.createNewSavingAccount(newAccount);
        System.out.printf("New savings account created: %s \n", acc);
    }

    public void createNewInvestmentAccount(int id){
        Account newAccount = new Account(id, 0.0f, 0.0f, 0.0f);
        Account acc = service.createNewInvestmentAccount(newAccount);
        System.out.printf("New investment account created: %s \n", acc);
    }

    public void getAllAccountsByUserID(int id){
        List<Account> accounts = service.getAllAccountsByUserID(id);
        System.out.println("Your Account Balances are: \n");
        for(Account acc : accounts){
            System.out.println("User ID: " + acc.getUserId() +" Account ID: " + acc.getAccountID() + " Gold:" + acc.getGold() + " Silver:" + acc.getSilver() + " Copper: " + acc.getCopper());
        }
    }

    public void depositPrompt(int id){
        getAllAccountsByUserID(id);
        System.out.println("Which Account do you wish to put a deposit in?(Please input the integer Account ID number)");
        String userActionIndicated = scanner.nextLine();
        int accId = Integer.parseInt(userActionIndicated);
        System.out.println("How much Gold?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float gold = Float.parseFloat(userActionIndicated);
        System.out.println("How much Silver?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float silver = Float.parseFloat(userActionIndicated);
        System.out.println("How much Copper?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float copper = Float.parseFloat(userActionIndicated);
        Account depositedAccount = service.depositInAccount(accId, gold, silver, copper);
        System.out.println("User ID: " + depositedAccount.getUserId() +" Account ID: " + depositedAccount.getAccountID() + " Gold:" + depositedAccount.getGold() + " Silver:" + depositedAccount.getSilver() + " Copper: " + depositedAccount.getCopper());
    }

    public void withdrawPrompt(int id){
        getAllAccountsByUserID(id);
        System.out.println("Which Account do you wish to withdraw from?(Please input the integer Account ID number)");
        String userActionIndicated = scanner.nextLine();
        int accId = Integer.parseInt(userActionIndicated);
        System.out.println("How much Gold?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float gold = Float.parseFloat(userActionIndicated);
        System.out.println("How much Silver?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float silver = Float.parseFloat(userActionIndicated);
        System.out.println("How much Copper?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float copper = Float.parseFloat(userActionIndicated);
        Account withdrawAccount = new Account();
        System.out.println("User ID: " + withdrawAccount.getUserId() +" Account ID: " + withdrawAccount.getAccountID() + " Gold:" + withdrawAccount.getGold() + " Silver:" + withdrawAccount.getSilver() + " Copper: " + withdrawAccount.getCopper());
    }

    public void deleteAccount(int id){
        getAllAccountsByUserID(id);
        System.out.println("Which Account do you wish to close?(Please input the integer Account ID number)");
        String userActionIndicated = scanner.nextLine();
        int accId = Integer.parseInt(userActionIndicated);
    }

}

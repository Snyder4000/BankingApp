package com.revature.controller;

import java.util.Scanner;
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
                    break;
                case "3":
                    System.out.println("Prompts for withdrawl will be placed here");
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
        Account newAccount = new Account(id, 0, 0, 0);
        Account acc = service.createNewCheckingAccount(newAccount);
        System.out.printf("New checking account created: %s \n", acc);
    }

    public void createNewSavingAccount(int id){
        Account newAccount = new Account(id, 0, 0, 0);
        Account acc = service.createNewSavingAccount(newAccount);
        System.out.printf("New savings account created: %s \n", acc);
    }

    public void createNewInvestmentAccount(int id){
        Account newAccount = new Account(id, 0, 0, 0);
        Account acc = service.createNewInvestmentAccount(newAccount);
        System.out.printf("New investment account created: %s \n", acc);
    }
}

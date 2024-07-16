package com.revature.controller;

import java.util.Scanner;
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
        System.out.println("1. Open new account");
        System.out.println("2. Get balance of all accounts");
        System.out.println("3. Withdraw from an account");
        System.out.println("4. Deposit into an account");
        System.out.println("5. Close banking account");
        System.out.println("q. Log out");
        try{
            String userActionIndicated = scanner.nextLine();
            switch (userActionIndicated) {
                case "1":
                    System.out.println("Checking account creations will go here, current User ID: " + controlMap.get("User ID"));
                    accountCreationPrompt(Integer.parseInt(controlMap.get("User ID")));
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
                    deleteAccountPrompt(Integer.parseInt(controlMap.get("User ID")));
                    break;
                case "q":
                    System.out.printf("Goodbye, %s!\n", controlMap.get("User"));
                    controlMap.put("User", "No one logged in");
                    controlMap.put("User ID", Integer.toString(0));
                    controlMap.put("Logged In", "false");
                    break;
                default:
                    System.out.println("INVAILD INPUT! Please input a selection option.");
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
        System.out.println("Your Account Balances are: \n");
        System.out.println("Your Checking Accounts are:");
        getAllCheckingByID(id);
        System.out.println("\nYour Savings Accounts are:");
        getAllSavingByID(id);
        System.out.println("\nYour Investment Accounts are:");
        getAllInvestmentsByID(id);
        System.out.println("");
    }

    public void getAllCheckingByID(int id){
        List<Account> accounts = service.getAllAccountsByUserIDAndType(id, 1);
        for(Account acc : accounts){
            System.out.printf("Account ID: %s, Gold: %.2f, Silver: %.2f, Copper: %.2f", acc.getAccountID(), acc.getGold(), acc.getSilver(), acc.getCopper());
        }
    }

    public void getAllSavingByID(int id){
        List<Account> savingAccounts = service.getAllAccountsByUserIDAndType(id, 2);
        for(Account acc : savingAccounts){
            System.out.printf("Account ID: %s, Gold: %.2f, Silver: %.2f, Copper: %.2f", acc.getAccountID(), acc.getGold(), acc.getSilver(), acc.getCopper());
        }
    }

    public void getAllInvestmentsByID(int id){
        List<Account> investmentAccounts = service.getAllAccountsByUserIDAndType(id, 3);
        for(Account acc : investmentAccounts){
            System.out.printf("Account ID: %s, Gold: %.2f, Silver: %.2f, Copper: %.2f", acc.getAccountID(), acc.getGold(), acc.getSilver(), acc.getCopper());
        }
    }

    public void accountCreationPrompt(int id){
        System.out.println("What type of Account do you want to open: ");
        accountTypePrompt();
        String userActionIndicated = scanner.nextLine();
        switch(userActionIndicated){
            case "1":
                createNewCheckingAccount(id);
                break;
            case "2":
                createNewSavingAccount(id);
                break;
            case "3":
                createNewInvestmentAccount(id);
                break;
            default:
                System.out.println("INVAILD INPUT! Please input a selection option.");
        }
    }

    public void depositPrompt(int id){
        System.out.println("What type of Account do you want to deposit into: ");
        accountTypePrompt();
        String userActionIndicated = scanner.nextLine();
        int type = Integer.parseInt(userActionIndicated);
        switch(type){
            case 1:
                getAllCheckingByID(id);
                break;
            case 2:
                getAllSavingByID(id);
                break;
            case 3:
                getAllInvestmentsByID(id);
            default:
                System.out.println("INVAILD INPUT! Please input a selection option.");
        }
        System.out.println("Which Account do you wish to put a deposit in?(Please input the integer Account ID number)");
        userActionIndicated = scanner.nextLine();
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
        Account depositedAccount = service.depositInAccount(type, accId, gold, silver, copper);
        System.out.printf("Account ID: %s, Gold: %.2f, Silver: %.2f, Copper: %.2f", depositedAccount.getAccountID(), depositedAccount.getGold(), depositedAccount.getSilver(), depositedAccount.getCopper());
    }

    public void withdrawPrompt(int id){
        System.out.println("What type of Account do you want to deposit into: ");
        accountTypePrompt();
        String userActionIndicated = scanner.nextLine();
        int type = Integer.parseInt(userActionIndicated);
        switch(type){
            case 1:
                getAllCheckingByID(id);
                break;
            case 2:
                getAllSavingByID(id);
                break;
            case 3:
                getAllInvestmentsByID(id);
            default:
                System.out.println("INVAILD INPUT! Please input a selection option.");
        }
        System.out.println("Which Account do you wish to withdraw from?(Please input the integer Account ID number)");
        userActionIndicated = scanner.nextLine();
        int accId = Integer.parseInt(userActionIndicated);
        Account withdrawAccount = service.getAccountByIDAndType(accId, type);
        System.out.println("How much Gold?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float gold = Float.parseFloat(userActionIndicated);
        System.out.println("How much Silver?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float silver = Float.parseFloat(userActionIndicated);
        System.out.println("How much Copper?(Please enter the amount)");
        userActionIndicated = scanner.nextLine();
        float copper = Float.parseFloat(userActionIndicated);
        if(withdrawAccount.getGold() >= gold && withdrawAccount.getSilver() >= silver && withdrawAccount.getCopper() >= copper){
            withdrawAccount = service.withdrawFromAccount(type, accId, gold, silver, copper);
            System.out.printf("Account ID: %s, Gold: %.2f, Silver: %.2f, Copper: %.2f", withdrawAccount.getAccountID(), withdrawAccount.getGold(), withdrawAccount.getSilver(), withdrawAccount.getCopper());
        }
        else{
            System.out.println("Insufficient funds for withdraw from the account.");
        }
    }

    public void accountTypePrompt(){
        System.out.println("1. checking account");
        System.out.println("2. saving account");
        System.out.println("3. investing account");
    }
    
    public void deleteAccountPrompt(int id){
        System.out.println("What type of Account do you want to close: ");
        accountTypePrompt();
        String userActionIndicated = scanner.nextLine();
        int type = Integer.parseInt(userActionIndicated);
        switch(type){
            case 1:
                getAllCheckingByID(id);
                break;
            case 2:
                getAllSavingByID(id);
                break;
            case 3:
                getAllInvestmentsByID(id);
            default:
                System.out.println("INVAILD INPUT! Please input a selection option.");
        }
        System.out.println("Which Account do you wish to close?(Please input the integer Account ID number)");
        userActionIndicated = scanner.nextLine();
        int accId = Integer.parseInt(userActionIndicated);
        service.deleteAccount(type, accId);
    }

}

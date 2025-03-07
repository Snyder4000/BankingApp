package com.revature;

import com.revature.controller.AccountController;
import com.revature.controller.UserController;
import com.revature.repository.AccountDao;
import com.revature.repository.SqliteAccountDao;
import com.revature.repository.SqliteUserDao;
import com.revature.repository.UserDao;
import com.revature.service.AccountService;
import com.revature.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(System.in)){
            UserDao userDao = new SqliteUserDao();
            UserService userService = new UserService(userDao);
            UserController userController = new UserController(scanner, userService);
            AccountDao accountDao = new SqliteAccountDao();
            AccountService accountService = new AccountService(accountDao);
            AccountController accountController = new AccountController(scanner, accountService);
            Map<String, String> controlMap = new HashMap<>();

            controlMap.put("Continue Loop", "true");
            controlMap.put("Logged In", "false");
            
            while(Boolean.parseBoolean(controlMap.get("Continue Loop"))){  
                if(Boolean.parseBoolean(controlMap.get("Logged In"))){
                    accountController.promptUserForAccountService(controlMap);
                }else{
                    userController.promptUserForService(controlMap);
                }
            }

        }

    }

}
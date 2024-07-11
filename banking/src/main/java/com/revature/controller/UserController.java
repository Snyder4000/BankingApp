package com.revature.controller;

import com.revature.exception.LoginFail;
import com.revature.service.UserService;
import com.revature.entity.User;
import java.util.Map;
import java.util.Scanner;

public class UserController {
    private Scanner scanner;
    private UserService userService;

    public UserController(Scanner scanner, UserService userService){
        this.scanner = scanner;
        this.userService = userService;
    }

    public void promptUserForService(Map<String,String> controlMap){
        System.out.println("Welcome to the First World Bank of Faerun! What would you like to do?");
        System.out.println("1. register an account");
        System.out.println("2. login");
        System.out.println("q. quit");
        try{
            String userActionIndicated = scanner.nextLine();
            switch (userActionIndicated) {
                case "1":
                    registerNewUser();
                    break;
                case "2":
                    User tempUser = login();
                    controlMap.put("User", tempUser.getUsername());
                    controlMap.put("User ID", Integer.toString(tempUser.getUserId()));
                    controlMap.put("Logged In", "true");
                    break;
                case "q":
                    System.out.println("Goodbye!");
                    controlMap.put("Continue Loop", "false");
            }
        } catch(LoginFail exception){
            System.out.println(exception.getMessage());
        }
    }

    

    public void registerNewUser(){
        User newCredentials = getUserCredentials();
        User newUser = userService.validateNewCredentials(newCredentials);
        System.out.printf("New account created: %s", newUser);
    }

    public User login(){
        return userService.checkLoginCredentials(getUserCredentials());
    }

    public User getUserCredentials(){
        String newUsername;
        String newPassword;
        System.out.print("Please enter a username: ");
        newUsername = scanner.nextLine();
        System.out.print("Please enter a password: ");
        newPassword = scanner.nextLine();
        return new User(newUsername, newPassword);
    }
}

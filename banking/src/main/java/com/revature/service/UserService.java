package com.revature.service;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.repository.UserDao;
import java.util.List;

public class UserService {
    
    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public User validateNewCredentials(User newUserCredentials){
        if (checkUsernamePasswordLength(newUserCredentials)){
            if(checkUsernameIsUnique(newUserCredentials)){
                return userDao.createUser(newUserCredentials);
            }
            else{
                User user = new User();
                return user;
            }
        }
        else{
            User user = new User();
            return user;
        }
        //throw new RuntimeException("Username already exists!");
    }

    public User checkLoginCredentials(User credentials){
        for (User user : userDao.getAllUsers()){
            boolean usernameMatches = user.getUsername().equals(credentials.getUsername());
            boolean passwordMatches = user.getPassword().equals(credentials.getPassword());
            if (usernameMatches && passwordMatches){
                credentials.setUserId(user.getUserId());
                return credentials;
            }
        }
        
        throw new LoginFail("Credentials are invalid: please try again");
    }

    private boolean checkUsernamePasswordLength(User newUserCredentials){
        boolean usernameIsValid = newUserCredentials.getUsername().length() <= 30;
        boolean passwordIsValid = newUserCredentials.getPassword().length() <= 30;
        return usernameIsValid && passwordIsValid;
    }

    private boolean checkUsernameIsUnique(User newUserCredentials){
        boolean usernameIsUnique = true;
        List<User> users = userDao.getAllUsers();
        for(User user : users){
            if(newUserCredentials.getUsername().equals(user.getUsername())){
                usernameIsUnique = false;
                break;
            }
        }
        return usernameIsUnique;
    }
}

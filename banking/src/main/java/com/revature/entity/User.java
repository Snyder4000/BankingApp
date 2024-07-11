package com.revature.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
    private String username;
    private String password;
    private int userid;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.userid = 0;
    }

    public int getUserId(){
        return userid;
    }

    public void setUserId(int id){
        userid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

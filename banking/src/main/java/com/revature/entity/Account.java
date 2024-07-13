package com.revature.entity;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

    private int user_id;
    private float gold;
    private float silver;
    private float copper;
    private int account_id;

    public Account(){}

    public Account(int id, float g, float s, float c){
        this.user_id = id;
        this.copper = c;
        this.silver = s;
        this.gold = g;
        this.account_id = 0;
    }

    public int getAccountID(){
        return account_id;
    }

    public void setAccountID(int id){
        account_id = id;
    }
    public int getUserId(){
        return user_id;
    }

    public void setUserId(int id){
        user_id = id;
    }

    public float getGold(){
        return gold;
    }

    public void setGold(float g){
        gold = g;
    }

    public float getSilver(){
        return silver;
    }

    public void setSilver(float s){
        silver = s;
    }

    public float getCopper(){
        return copper;
    }

    public void setCopper(float c){
        copper = c;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account acc = (Account) o;
        return Objects.equals(getAccountID(), acc.getAccountID());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUserId(), getGold(), getSilver(), getCopper(), getAccountID());
    }

    @Override
    public String toString(){
        return "Account{" + 
                "account_id: " + account_id + ",\n "+
                "user_id: " + user_id + ",\n "+
                "gold: " + gold + ",\n " + 
                "silver: " + silver + ",\n " +
                "copper: " + copper +'}';
    }
}

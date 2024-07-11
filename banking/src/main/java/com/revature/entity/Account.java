package com.revature.entity;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

    private int user_id;
    private float gold;
    private float silver;
    private float copper;

    public Account(){}

    public Account(int id, float g, float s, float c){
        this.user_id = id;
        this.copper = c;
        this.silver = s;
        this.gold = g;
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
        return Objects.equals(getUserId(), acc.getUserId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUserId(), getGold(), getSilver(), getCopper());
    }

    @Override
    public String toString(){
        return "Account{" + 
                "user_id: " + user_id + ",\n "+
                "gold: " + gold + ",\n " + 
                "silver: " + silver + ",\n " +
                "copper: " + copper +'}';
    }
}

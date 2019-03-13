package com.example.awesomefat.csc518_listexample;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class LoyaltyProgram implements Serializable
{
    public String name;
    public String bank;
    public int point_balance;
    private String key;
    private DatabaseReference ref;

    public LoyaltyProgram(String name, String bank)
    {
        this.name = name;
        this.bank = bank;
        this.point_balance = 0;
    }

    public LoyaltyProgram()
    {

    }

    public LoyaltyProgram(String name, String bank, int point_balance)
    {
        this(name, bank);
        this.point_balance = point_balance;
    }

    public void delete()
    {
        this.ref.removeValue();
    }

    public void save(String name, String bank, int point_balance)
    {
        this.name = name;
        this.bank = bank;
        this.point_balance = point_balance;
        this.save();
    }

    public void save()
    {
        //save to the database the current state of this LoyaltyProgram
        this.ref.setValue(this);
    }

    public void setKey(String key)
    {
        this.key = key;
        this.ref = Core.loyaltyProgramRef.child(this.key);
    }

    public String toString()
    {
        return this.name + " - " + this.bank + " - " + this.point_balance;
    }

    public void display()
    {
        System.out.println(this.name + " - " + this.bank + " - " + this.point_balance);
    }

    public String getName() {
        return name;
    }

    public String getBank() {
        return bank;
    }

    public int getPoint_balance() {
        return point_balance;
    }
}

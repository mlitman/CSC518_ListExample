package com.example.awesomefat.csc518_listexample;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class CreditCard implements Serializable
{
    public String name;
    public String start_date;
    public int min_spend;
    public int point_bonus;
    private String key;
    private DatabaseReference ref;

    public CreditCard(String name, String start_date, int min_spend, int point_bonus)
    {
        this.name = name;
        this.start_date = start_date;
        this.min_spend = min_spend;
        this.point_bonus = point_bonus;
    }

    //no argument constructor required for de-serialization
    public CreditCard()
    {
    }

    public void setKey(String key)
    {
        this.key = key;
        this.ref = Core.creditCardRef.child(this.key);
    }

    public void save()
    {
        this.ref.setValue(this);
    }

    public void delete()
    {
        this.ref.removeValue();
    }

    public String toString()
    {
        return "Name: " + this.name +
                " (" + this.start_date + ") - Min Spend: "
                + this.min_spend + " - Bonus: " + this.point_bonus;
    }

    public void display()
    {
        System.out.println("*****Name: " + this.name +
                " (" + this.start_date + ") - Min Spend: "
        + this.min_spend + " - Bonus: " + this.point_bonus);
    }

}

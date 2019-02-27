package com.example.awesomefat.csc518_listexample;

import java.io.Serializable;

public class CreditCard implements Serializable
{
    public String name;
    public String start_date;
    public int min_spend;
    public int point_bonus;

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

    public String getName()
    {
        return name;
    }

    public String getStart_date() {
        return start_date;
    }

    public int getMin_spend() {
        return min_spend;
    }

    public int getPoint_bonus() {
        return point_bonus;
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

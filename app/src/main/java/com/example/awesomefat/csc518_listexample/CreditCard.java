package com.example.awesomefat.csc518_listexample;

public class CreditCard
{
    private String name;
    private String start_date;
    private int min_spend;
    private int point_bonus;

    public CreditCard(String name, String start_date, int min_spend, int point_bonus)
    {
        this.name = name;
        this.start_date = start_date;
        this.min_spend = min_spend;
        this.point_bonus = point_bonus;
    }

    public String toString()
    {
        return "Name: " + this.name +
                " (" + this.start_date + ") - Min Spend: "
                + this.min_spend + " - Bonus: " + this.point_bonus;
    }

    public void display()
    {
        System.out.println("Name: " + this.name +
                " (" + this.start_date + ") - Min Spend: "
        + this.min_spend + " - Bonus: " + this.point_bonus);
    }

}

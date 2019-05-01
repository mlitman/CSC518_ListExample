package com.example.awesomefat.csc518_listexample;

import java.io.Serializable;

public class Airport implements Serializable
{
    public String name;
    public String country;
    public String region;
    public String city;
    public String airportCode;

    public Airport(String name, String country, String region, String city, String airportCode)
    {
        this.name = name;
        this.country = country;
        this.region = region;
        this.city = city;
        this.airportCode = airportCode;
    }

    public Airport()
    {

    }

    private String removeQuotes(String s)
    {
        String result = "";
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) != '"')
            {
                result += s.charAt(i);
            }
        }
        return result;
    }

    public boolean isLegalCode()
    {
        return this.airportCode.length() == 3;
    }

    public void sanitize()
    {
        this.name = this.removeQuotes(this.name);
        this.country = this.removeQuotes(this.country);
        this.airportCode = this.removeQuotes(this.airportCode);
        this.city = this.removeQuotes(this.city);
        this.region = this.removeQuotes(this.region);
        this.display();
    }

    public boolean filterApplies(String filterString)
    {
        filterString = filterString.toLowerCase();

        return this.name.toLowerCase().contains(filterString) ||
                this.city.toLowerCase().contains(filterString) ||
                this.region.toLowerCase().contains(filterString) ||
                this.airportCode.toLowerCase().contains(filterString) ||
                this.country.toLowerCase().contains(filterString);
    }

    public String toString()
    {
        return this.city + " | " + this.region + " | " +  this.country + "(" + this.airportCode + ")";
    }

    public void display()
    {
        System.out.println("*** " + this.name + "\t" + this.country + "\t" + this.region + "\t" + this.city + "\t" + this.airportCode);
    }
}

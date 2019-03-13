package com.example.awesomefat.csc518_listexample;

import com.google.firebase.database.DatabaseReference;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class NetworkThread extends Thread
{
    private String airportCode;

    public NetworkThread(String airportCode)
    {
        this.airportCode = airportCode;
    }

    public void run()
    {
        try
        {
            URL airportURL = new URL("https://www.flightsfrom.com/" + "MKE" + "/destinations");

            HttpURLConnection conn = (HttpURLConnection)airportURL.openConnection();
            Scanner input = new Scanner(conn.getInputStream());
            String data = "";

            while(input.hasNextLine())
            {
                data = data + input.nextLine();
            }
            //System.out.println("***" + data);
            String[] parts = data.split("airport-content-destination-list-name");
            String beforeVal = "destination-search-item\">";
            String afterVal = "</span>";
            int beforeIndex, afterIndex;

            for(String part : parts)
            {
                beforeIndex = part.indexOf(beforeVal);
                if(beforeIndex != -1)
                {
                    beforeIndex += beforeVal.length();
                    afterIndex = part.indexOf(afterVal, beforeIndex);
                    System.out.println("***" + part.substring(beforeIndex, afterIndex));
                }
            }
            System.out.println("*** Done");

        }
        catch(Exception e)
        {
            System.out.println("***" + e.toString());
        }
    }
}

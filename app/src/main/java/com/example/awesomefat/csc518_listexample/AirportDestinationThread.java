package com.example.awesomefat.csc518_listexample;

import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class AirportDestinationThread extends Thread
{
    private String airportCode;
    private LinkedList<String> destinationStrings;
    private ArrayAdapter<String> aa;
    private AirportDestinationThread myself;
    private String currDateFrom = "2019-01-01";
    private String currDateTo = "2019-12-31";

    public AirportDestinationThread(String airportCode, LinkedList<String> destinationStrings, ArrayAdapter<String> aa)
    {
        this.airportCode = airportCode;
        this.destinationStrings = destinationStrings;
        this.aa = aa;
        this.myself = this;
    }

    public AirportDestinationThread(String airportCode, String monthNum, String monthEnd, LinkedList<String> destinationStrings, ArrayAdapter<String> aa)
    {
        this(airportCode, destinationStrings, aa);
        this.currDateFrom = "2019-" + monthNum + "-01";
        this.currDateTo = "2019-" + monthNum + "-" + monthEnd;
    }

    //get the data from screen scraping, add it to the linked list, and notify the array adapter
    public void run()
    {
        try
        {
            String urlString = String.format("https://www.flightsfrom.com/%s/destinations?dateMethod=month&dateFrom=%s&dateTo=%s", this.airportCode, this.currDateFrom, this.currDateTo);
            URL airportURL = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection)airportURL.openConnection();
            Scanner input = new Scanner(conn.getInputStream());
            String data = "";

            while(input.hasNextLine())
            {
                data = data + input.nextLine();
            }
            input.close();
            System.out.println("*** HAVE DATA!!!!!");
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
                    //System.out.println("***" + part.substring(beforeIndex, afterIndex));
                    this.destinationStrings.add(part.substring(beforeIndex, afterIndex));
                }
            }
            System.out.println("*** Done");

            new Handler(Looper.getMainLooper()).post(new Runnable () {
                @Override
                public void run () {
                    myself.aa.notifyDataSetChanged();
                    DatabaseReference ref = Core.database.getReference("airport_code_cache").child(myself.airportCode.toUpperCase());
                    ref.setValue(myself.destinationStrings);
                }
            });


        }
        catch(Exception e)
        {
            System.out.println("***" + e.toString());
        }
    }
}

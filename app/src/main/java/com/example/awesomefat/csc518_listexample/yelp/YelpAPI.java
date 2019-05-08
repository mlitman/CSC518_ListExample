package com.example.awesomefat.csc518_listexample.yelp;

import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class YelpAPI extends Thread
{
    private String cityState;
    private LinkedList<Restaurant> theRestaurantNames;
    private ArrayAdapter<Restaurant> aa;
    private YelpAPI myself;

    public YelpAPI(String cityState, LinkedList<Restaurant> theRestaurantNames, ArrayAdapter<Restaurant> aa)
    {
        this.myself = this;
        this.cityState = cityState;
        this.theRestaurantNames = theRestaurantNames;
        this.aa = aa;
    }

    public void run()
    {
        try
        {
            URL airportURL = new URL("https://api.yelp.com/v3/businesses/search?location=" + this.cityState + "&categories=restaurants");
            HttpURLConnection conn = (HttpURLConnection)airportURL.openConnection();
            conn.setRequestProperty("Authorization", "Bearer _fudvZjjQRmQpuzZTXjRDPxJeLlYw8rGTsrzZNknmb_B7AA7ilLpaYgwHl-nM8UmgEKWI3rs7DPYseiuGzUmEybSLzLiGIiQVLFExouUcl-mKZQtHTymfnB1AgDJXHYx");
            Scanner input = new Scanner(conn.getInputStream());
            String data = "";

            while(input.hasNextLine())
            {
                data = data + input.nextLine();
            }
            input.close();
            System.out.println("*** DATA: " + data);
            this.theRestaurantNames.clear();
            JSONObject obj = new JSONObject(data);
            JSONArray businesses = obj.getJSONArray("businesses");
            for(int i = 0; i < businesses.length(); i++)
            {
                JSONObject tempObj = businesses.getJSONObject(i);
                String name = tempObj.getString("name");
                String imageURL = tempObj.getString("image_url");
                String theURL = tempObj.getString("url");
                String reviewCount = tempObj.getString("review_count");
                String rating = tempObj.getString("rating");
                String phone = tempObj.getString("display_phone");
                JSONObject locObj = tempObj.getJSONObject("location");
                String address1 = locObj.getString("address1");
                String city = locObj.getString("city");
                String state = locObj.getString("state");
                String zip = locObj.getString("zip_code");
                String formattedAddress = String.format("%s\n%s,%s %s", address1, city,state,zip);
                System.out.println("*** " + name);
                this.theRestaurantNames.add(new Restaurant(name, phone, formattedAddress, theURL, imageURL, reviewCount, rating));
            }
            new Handler(Looper.getMainLooper()).post(new Runnable () {
                @Override
                public void run () {
                    myself.aa.notifyDataSetChanged();
                }
            });

        }
        catch(Exception e)
        {
            System.out.println("*** " + e.toString());
        }
    }
}

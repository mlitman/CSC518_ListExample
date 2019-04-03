package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class AirportDetailActivity extends AppCompatActivity
{
    private ListView destinationsLV;
    private AirportDetailActivity myself;
    private LinkedList<String> ll;
    private AirportCodeCache acc;
    private ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.myself = this;
        setContentView(R.layout.activity_airport_detail);
        final TextView airportTV = this.findViewById(R.id.airportTV);
        this.destinationsLV = this.findViewById(R.id.destinationsLV);

        this.ll = new LinkedList<String>();
        this.aa = new ArrayAdapter<String>(this, R.layout.another_row, ll);
        this.destinationsLV.setAdapter(aa);

        this.destinationsLV.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long row_id)
            {
                String selectedAirport = ll.get(position).trim();

                //alternative way of getting code and name
                //String ac = selectedAirport.substring(selectedAirport.length()-3);
                //String city = selectedAirport.substring(0, selectedAirport.length()-3);

                String[] parts = selectedAirport.split(" ");

                Intent i = new Intent(myself, AirportDetailActivity.class);
                i.putExtra("airportCode", parts[parts.length-1].trim());
                String cityName = "";
                for(int j = 0; j < parts.length-1; j++)
                {
                    cityName = cityName + parts[j] + " ";
                }
                i.putExtra("cityName", cityName);
                Core.currentItinerary.push(cityName + " " + parts[parts.length-1].trim());
                myself.startActivity(i);
            }
        });


        String cityName = this.getIntent().getStringExtra("cityName");
        String airportCode = this.getIntent().getStringExtra("airportCode");
        airportCode = airportCode.replaceAll("\"","");
        airportTV.setText(cityName + " - " + airportCode);

        this.acc = new AirportCodeCache(airportCode);
        acc.getData(aa, ll);

        //Can't get airport stuff from cache
        //NetworkThread nt = new NetworkThread(airportCode, aa, ll);
        //nt.setPriority(Thread.MAX_PRIORITY);
        //nt.start();

        //strip the " from both ends of the airport code


    }

    public void onDisplayItineraryButtonPressed(View v)
    {
        Core.currentItinerary.display();
    }

    @Override
    public void onBackPressed()
    {
        Core.currentItinerary.pop();
        super.onBackPressed();
    }

    public void onReloadCacheButtonPressed(View v)
    {
        this.acc.clearCache(this.aa, this.ll);
    }
}

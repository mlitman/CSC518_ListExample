package com.example.awesomefat.csc518_listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItineraryActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        ListView itineraryLV = this.findViewById(R.id.itineraryLV);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.another_row, Core.currentItinerary.getAsList());
        itineraryLV.setAdapter(aa);
    }
}

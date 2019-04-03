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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.myself = this;
        setContentView(R.layout.activity_airport_detail);
        final TextView airportTV = this.findViewById(R.id.airportTV);
        this.destinationsLV = this.findViewById(R.id.destinationsLV);

        this.ll = new LinkedList<String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.another_row, ll);
        this.destinationsLV.setAdapter(aa);

        this.destinationsLV.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long row_id)
            {
                String selectedAirport = ll.get(position);
                String[] parts = selectedAirport.split(" ");

                Intent i = new Intent(myself, AirportDetailActivity.class);
                i.putExtra("airportCode", parts[parts.length-1].trim());
                myself.startActivity(i);
            }
        });


        String airportCode = this.getIntent().getStringExtra("airportCode");
        airportCode = airportCode.replaceAll("\"","");
        airportTV.setText(airportCode);
        NetworkThread nt = new NetworkThread(airportCode, aa, ll);
        nt.setPriority(Thread.MAX_PRIORITY);
        nt.start();

        //strip the " from both ends of the airport code


    }
}

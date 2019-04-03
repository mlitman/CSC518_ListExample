package com.example.awesomefat.csc518_listexample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class AirportCodeCache
{
    private String airportCode;
    private LinkedList<String> destinations;
    private AirportCodeCache myself;


    public AirportCodeCache(String airportCode) {
        this.airportCode = airportCode;
        this.destinations = new LinkedList<String>();
        this.myself = this;
    }

    public void clearCache(final ArrayAdapter<String> aa, final LinkedList<String> ll)
    {
        DatabaseReference ref = Core.database.getReference("airport_code_cache").child(this.airportCode.toUpperCase());
        ref.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference)
            {
                ll.clear();
                aa.notifyDataSetChanged();
                myself.getDataScreenScrape(aa,ll);
            }
        });
    }

    private void getDataScreenScrape(final ArrayAdapter<String> aa, final LinkedList<String> ll)
    {
        AirportDestinationThread adt = new AirportDestinationThread(airportCode, ll, aa);
        adt.setPriority(Thread.MAX_PRIORITY);
        adt.start();
    }

    //one way or another, he will fill ll with the data, and notify aa of the changes
    public void getData(final ArrayAdapter<String> aa, final LinkedList<String> ll)
    {
        //try to get from cache first
        DatabaseReference ref = Core.database.getReference("airport_code_cache").child(this.airportCode.toUpperCase());
        ref.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.getValue() == null)
                {
                    System.out.println("*** NOT IN CACHE!!!");
                    //get the data the old fashioned way and add it to our cache
                    myself.getDataScreenScrape(aa,ll);
                }
                else
                {
                    System.out.println("*** IS IN CACHE - Load the LinkedList!!!");
                    System.out.println("*** " + dataSnapshot.toString());
                    //aa.notifyDataSetChanged();
                    for(DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        System.out.println("*** " + ds.getValue());
                        ll.add((String)ds.getValue());
                    }
                    aa.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}

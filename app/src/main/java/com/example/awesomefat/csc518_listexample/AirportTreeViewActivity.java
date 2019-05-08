package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.awesomefat.csc518_listexample.AirportTree.ATree;
import com.example.awesomefat.csc518_listexample.yelp.Restaurant;
import com.example.awesomefat.csc518_listexample.yelp.YelpAPI;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class AirportTreeViewActivity extends AppCompatActivity
{
    private ATree theTree;
    private Button leftButton, rightButton;
    private TextView airportCodeTV, locationTV;
    private ListView restaurantLV;
    private LinkedList<Restaurant> theRestaurants;
    private AirportTreeViewActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_tree_view);

        this.myself = this;
        this.theTree = Core.currTree;
        this.leftButton = this.findViewById(R.id.leftButton);
        this.rightButton = this.findViewById(R.id.rightButton);
        this.airportCodeTV = this.findViewById(R.id.airportCodeTV);
        this.locationTV = this.findViewById(R.id.locationTV);
        this.restaurantLV = this.findViewById(R.id.restaurantLV);
        this.theRestaurants = new LinkedList<Restaurant>();

        this.restaurantLV.setAdapter(new ArrayAdapter<Restaurant>(this, R.layout.restaurant_row, this.theRestaurants){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView tv = (TextView)convertView;
                if(tv == null)
                {
                    tv = new TextView(this.getContext());
                }
                Restaurant r = this.getItem(position);
                tv.setText(r.getName());
                return tv;
            }
        });

        this.restaurantLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Core.currSelectedRestaurant = myself.theRestaurants.get(position);
                Intent i = new Intent(myself, RestaurantDetailActivity.class);
                myself.startActivity(i);
            }
        });
        this.airportCodeTV.setText(this.theTree.payload.airportCode);
        String location = this.theTree.payload.city + ", " + this.theTree.payload.region.split("-")[1];

        //asynchronous thread
        YelpAPI yelp = new YelpAPI(location, theRestaurants, (ArrayAdapter<Restaurant>)this.restaurantLV.getAdapter());
        yelp.start();

        this.locationTV.setText(location);
        this.hideButtonsIfNeeded();


    }

    public void onLeftButtonClicked(View v)
    {
        Core.currTree = this.theTree.left;
        Intent i = new Intent(this, AirportTreeViewActivity.class);
        this.startActivity(i);
    }

    public void onRightButtonClicked(View v)
    {
        Core.currTree = this.theTree.right;
        Intent i = new Intent(this, AirportTreeViewActivity.class);
        this.startActivity(i);
    }

    private void hideButtonsIfNeeded()
    {
        if(this.theTree.left == null)
        {
            this.leftButton.setVisibility(View.INVISIBLE);
        }

        if(this.theTree.right == null)
        {
            this.rightButton.setVisibility(View.INVISIBLE);
        }
    }
}

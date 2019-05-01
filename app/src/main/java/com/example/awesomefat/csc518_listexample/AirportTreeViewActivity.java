package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.awesomefat.csc518_listexample.AirportTree.ATree;

public class AirportTreeViewActivity extends AppCompatActivity
{
    private ATree theTree;
    private Button leftButton, rightButton;
    private TextView airportCodeTV, locationTV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_tree_view);

        this.theTree = Core.currTree;
        this.leftButton = this.findViewById(R.id.leftButton);
        this.rightButton = this.findViewById(R.id.rightButton);
        this.airportCodeTV = this.findViewById(R.id.airportCodeTV);
        this.locationTV = this.findViewById(R.id.locationTV);
        this.airportCodeTV.setText(this.theTree.payload.airportCode);
        String location = this.theTree.payload.city + ", " + this.theTree.payload.region.split("-")[1];
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

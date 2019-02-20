package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView creditCardLV, loyaltyProgramLV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 1000; i++)
        {
            Core.theLoyaltyProgramStrings[i] = "N/A";
            Core.theCreditCardStrings[i] = "N/A";
            Core.theCreditCards[i] = new CreditCard();
        }

        this.creditCardLV = (ListView)this.findViewById(R.id.creditCardListView);
        this.loyaltyProgramLV = (ListView)this.findViewById(R.id.loyaltyProgramListView);
        //Core.ccAdapter = new ArrayAdapter(this, R.layout.student_listview_row, Core.theCreditCardStrings);
        Core.ccCustomAdapter = new CreditCardArrayAdapter(this, R.layout.custom_credit_card_row, Core.theCreditCards);
        Core.lpAdapter = new ArrayAdapter(this, R.layout.student_listview_row, Core.theLoyaltyProgramStrings);

        this.creditCardLV.setAdapter(Core.ccCustomAdapter);
        this.loyaltyProgramLV.setAdapter(Core.lpAdapter);

    }

    public void onAddCreditCardButtonPressed(View v)
    {
        Intent i = new Intent(this, AddCreditCardActivity.class);
        this.startActivity(i);
    }

    public void onAddLoyaltyProgramButtonPressed(View v)
    {
        Intent i = new Intent(this, AddLoyaltyProgramActivity.class);
        this.startActivity(i);
    }

}

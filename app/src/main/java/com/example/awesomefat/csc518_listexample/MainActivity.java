package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView creditCardLV, loyaltyProgramLV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        DatabaseReference myRef2 = database.getReference("products");
        //myRef.setValue("Hello, World!");
        //myRef2.setValue("LOL");

        //asynchronous call (non-blocking call) - Observer Design Pattern
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                System.out.println("********* " + value);

            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value

            }
        });


        this.creditCardLV = (ListView)this.findViewById(R.id.creditCardListView);
        this.loyaltyProgramLV = (ListView)this.findViewById(R.id.loyaltyProgramListView);

        Core.ccCustomAdapter = new CreditCardArrayAdapterForLinkedLists(this,
                R.layout.custom_credit_card_row, Core.theCreditCardsLL);
        Core.lpCustomAdapter = new LoyaltyProgramArrayAdapterForLinkedLists(this,
                R.layout.loyalty_program_custom_row, Core.theLoyaltyProgramsLL);

        this.creditCardLV.setAdapter(Core.ccCustomAdapter);
        this.loyaltyProgramLV.setAdapter(Core.lpCustomAdapter);

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

package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.awesomefat.csc518_listexample.Core.database;

public class MainActivity extends AppCompatActivity {

    private ListView creditCardLV, loyaltyProgramLV;
    private MainActivity myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myContext = this;

        Core.database = FirebaseDatabase.getInstance();
        Core.creditCardRef = database.getReference("creditCards");
        Core.loyaltyProgramRef = database.getReference("loyaltyPrograms");

        Core.loyaltyProgramRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                System.out.println("****** " + dataSnapshot.toString());
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //System.out.println("********* " + dataSnapshot.toString());
                Core.theLoyaltyProgramsLL.removeAll();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    //System.out.println("********* " + ds.toString());
                    //de-serialize the card
                    System.out.println("*** Adding value");
                    LoyaltyProgram tempLP = ds.getValue(LoyaltyProgram.class);
                    tempLP.setKey(ds.getKey());
                    Core.addLoyaltyProgramLocally(tempLP);
                }
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                System.out.println("***" + error.toString());

            }
        });

        //asynchronous call (non-blocking call) - Observer Design Pattern
        Core.creditCardRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                System.out.println("****** " + dataSnapshot.toString());
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //System.out.println("********* " + dataSnapshot.toString());
                Core.theCreditCardsLL.removeAll();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    //System.out.println("********* " + ds.toString());
                    //de-serialize the card
                    System.out.println("*** Adding value");
                    CreditCard tempCC = ds.getValue(CreditCard.class);
                    tempCC.setKey(ds.getKey());
                    Core.addCreditCardLocally(tempCC);
                }



            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                System.out.println("***" + error.toString());

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

        this.creditCardLV.setClickable(true);
        this.creditCardLV.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long row_id)
            {
                CreditCard selectedCard = Core.theCreditCardsLL.getAtIndex(position);
                Intent i = new Intent(myContext, EditCreditCardActivity.class);
                Core.currentSelectedCard = selectedCard;
                myContext.startActivity(i);

            }
        });

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

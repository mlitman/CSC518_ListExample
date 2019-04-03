package com.example.awesomefat.csc518_listexample;

import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//singleton - a class filled with stuff acccccebile by everything
public class Core
{
    public static LinkedListOfCreditCards theCreditCardsLL = new LinkedListOfCreditCards();
    public static LinkedListOfLoyaltyPrograms theLoyaltyProgramsLL = new LinkedListOfLoyaltyPrograms();
    public static CreditCardArrayAdapterForLinkedLists ccCustomAdapter;
    public static LoyaltyProgramArrayAdapterForLinkedLists lpCustomAdapter;
    public static FirebaseDatabase database;
    public static DatabaseReference creditCardRef;
    public static DatabaseReference loyaltyProgramRef;
    public static CreditCard currentSelectedCard = null;
    public static LoyaltyProgram currentSelectedLoyaltyProgram = null;
    public static ItineraryStack currentItinerary = new ItineraryStack();

    public static void addLoyaltyProgramToFirebase(LoyaltyProgram lp)
    {
        Core.loyaltyProgramRef.push().setValue(lp);
    }

    //encapsulated
    public static void addLoyaltyProgramLocally(LoyaltyProgram lp)
    {
        //happens in a static context
        Core.theLoyaltyProgramsLL.addAtEnd(lp);
        Core.lpCustomAdapter.notifyDataSetChanged();
    }

    public static void addCreditCardToFirebase(CreditCard cc)
    {
        Core.creditCardRef.push().setValue(cc);
    }

    public static void addCreditCardLocally(CreditCard cc)
    {
        Core.theCreditCardsLL.addEnd(cc);
        Core.ccCustomAdapter.notifyDataSetChanged();
    }
}

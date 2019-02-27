package com.example.awesomefat.csc518_listexample;

import android.widget.ArrayAdapter;

//singleton - a class filled with stuff acccccebile by everything
public class Core
{
    public static LinkedListOfCreditCards theCreditCardsLL = new LinkedListOfCreditCards();
    public static LinkedListOfLoyaltyPrograms theLoyaltyProgramsLL = new LinkedListOfLoyaltyPrograms();
    public static CreditCardArrayAdapterForLinkedLists ccCustomAdapter;
    public static LoyaltyProgramArrayAdapterForLinkedLists lpCustomAdapter;

    //encapsulated
    public static void addLoyaltyProgram(LoyaltyProgram lp)
    {
        //happens in a static context
        Core.theLoyaltyProgramsLL.addAtEnd(lp);
        Core.lpCustomAdapter.notifyDataSetChanged();
    }

    public static void addCreditCard(CreditCard cc)
    {
        Core.theCreditCardsLL.addEnd(cc);
        Core.ccCustomAdapter.notifyDataSetChanged();
    }
}

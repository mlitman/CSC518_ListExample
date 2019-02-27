package com.example.awesomefat.csc518_listexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LoyaltyProgramArrayAdapterForLinkedLists extends ArrayAdapter
{
    private LinkedListOfLoyaltyPrograms loyaltyProgramLinkedList;
    private int textViewResourceId;

    public LoyaltyProgramArrayAdapterForLinkedLists(Context context, int textViewResourceId,
                                                LinkedListOfLoyaltyPrograms list)
    {
        super(context, textViewResourceId);
        this.loyaltyProgramLinkedList = list;
        this.textViewResourceId = textViewResourceId;
    }

    @Override
    public int getCount()
    {
        return this.loyaltyProgramLinkedList.length();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem == null)
        {
            listItem = LayoutInflater.from(super.getContext()).
                    inflate(this.textViewResourceId,parent,false);
        }

        TextView lpName = (TextView)listItem.findViewById(R.id.lpProgramNameTV);
        TextView lpBank = (TextView)listItem.findViewById(R.id.lpBankNameTV);
        TextView lpPoints = (TextView)listItem.findViewById(R.id.lpPointsTV);
        LoyaltyProgram lp = this.loyaltyProgramLinkedList.getAtIndex(position);

        lpName.setText(lp.getName());
        lpBank.setText(lp.getBank());
        lpPoints.setText("" + lp.getPoint_balance());
        //return the View after we have set all of the values
        return listItem;
    }
}
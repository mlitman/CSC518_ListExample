package com.example.awesomefat.csc518_listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddLoyaltyProgramActivity extends AppCompatActivity
{
    private EditText loyaltyNameET, loyaltyBankET, loyaltyPointsET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loyalty_program);

        this.loyaltyNameET = (EditText)this.findViewById(R.id.loyaltyNameET);
        this.loyaltyBankET = (EditText)this.findViewById(R.id.loyaltyBankNameET);
        this.loyaltyPointsET = (EditText)this.findViewById(R.id.loyaltyCurrPointsET);
    }



    public void onSubmitButtonPressed(View v)
    {
        String programName = this.loyaltyNameET.getText().toString();
        String bankName = this.loyaltyBankET.getText().toString();
        int currPoints = Integer.parseInt(this.loyaltyPointsET.getText().toString());

        LoyaltyProgram lp = new LoyaltyProgram(programName, bankName, currPoints);
        lp.display();
        Core.addLoyaltyProgram(lp);
        this.finish();
    }
}

package com.example.awesomefat.csc518_listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditLoyaltyProgramActivity extends AppCompatActivity
{
    private EditText loyaltyNameET, loyaltyBankNameET, loyaltyCurrentPointsET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_loyalty_program);
        this.loyaltyNameET = this.findViewById(R.id.loyaltyNameET);
        this.loyaltyBankNameET = this.findViewById(R.id.loyaltyBankNameET);
        this.loyaltyCurrentPointsET = this.findViewById(R.id.loyaltyCurrPointsET);

        this.loyaltyNameET.setText(Core.currentSelectedLoyaltyProgram.name);
        this.loyaltyBankNameET.setText(Core.currentSelectedLoyaltyProgram.bank);
        this.loyaltyCurrentPointsET.setText("" + Core.currentSelectedLoyaltyProgram.point_balance);
    }

    public void onDeleteButtonClicked(View v)
    {
        Core.currentSelectedLoyaltyProgram.delete();
        this.finish();
    }

    public void onUpdateButtonClicked(View v)
    {
        Core.currentSelectedLoyaltyProgram.save(this.loyaltyNameET.getText().toString(),
                this.loyaltyBankNameET.getText().toString(),
                Integer.parseInt(this.loyaltyCurrentPointsET.getText().toString()));
        this.finish();
    }
}

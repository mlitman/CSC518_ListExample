package com.example.awesomefat.csc518_listexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditCreditCardActivity extends AppCompatActivity
{
    private EditText creditCardNameET, creditCardStartDateET,
            creditCardMinSpendET, creditCardBonusPointET;
    private EditCreditCardActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_credit_card);

        this.myself = this;

        this.creditCardNameET = (EditText)this.findViewById(R.id.creditCardNameET);
        this.creditCardStartDateET = (EditText)this.findViewById(R.id.creditCardStartDate);
        this.creditCardMinSpendET = (EditText)this.findViewById(R.id.creditCardMinSpendET);
        this.creditCardBonusPointET = (EditText)this.findViewById(R.id.creditCardBonusPointsET);

        this.creditCardNameET.setText(Core.currentSelectedCard.name);
        this.creditCardBonusPointET.setText(Core.currentSelectedCard.point_bonus + "");
        this.creditCardMinSpendET.setText(Core.currentSelectedCard.min_spend + "");
        this.creditCardStartDateET.setText(Core.currentSelectedCard.start_date);
    }

    public void onDeleteButtonPressed(View v)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure?");
        dialog.setTitle("Warning!!!!");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Core.currentSelectedCard.delete();
                myself.finish();
            }
        });

        dialog.setNeutralButton("Huh?", null);
        dialog.setNegativeButton("No", null);
        dialog.show();


    }

    public void onUpdateButtonPressed(View v)
    {
        String creditCardName = this.creditCardNameET.getText().toString();
        String creditCardStartDate = this.creditCardStartDateET.getText().toString();
        int creditCardMinSpend = Integer.parseInt(this.creditCardMinSpendET.getText().toString());
        int creditCardBonusPoints = Integer.parseInt(this.creditCardBonusPointET.getText().toString());
        Core.currentSelectedCard.name = creditCardName;
        Core.currentSelectedCard.start_date = creditCardStartDate;
        Core.currentSelectedCard.min_spend = creditCardMinSpend;
        Core.currentSelectedCard.point_bonus = creditCardBonusPoints;
        Core.currentSelectedCard.save();
        this.finish();
    }
}

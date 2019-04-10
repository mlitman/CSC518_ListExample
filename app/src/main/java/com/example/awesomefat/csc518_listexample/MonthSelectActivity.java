package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MonthSelectActivity extends AppCompatActivity
{

    private String airportCode;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_select);

        this.airportCode = this.getIntent().getStringExtra("airportCode");
        this.cityName = this.getIntent().getStringExtra("cityName");
    }

    public void monthButtonPressed(View v)
    {
        Button b = (Button)v;
        String buttonText = b.getText().toString();


        String[] monthNumbers = "01,02,03,04,05,06,07,08,09,10,11,12".split(",");
        String[] monthLastDays = "31,28,31,30,31,30,31,31,30,31,30,31".split(",");
        String monthNum = "";
        String monthLastDay = "";

        String[] months = "Jan,Feb,March,April,May,June,July,August,Sept,Oct,Nov,Dec".split(",");
        for(int i = 0; i < months.length; i++)
        {
            if(months[i].equals(buttonText))
            {
                monthNum = monthNumbers[i];
                monthLastDay = monthLastDays[i];
                break;
            }
        }
        Intent i = new Intent(this, AirportMonthDetailActivity.class);
        i.putExtra("monthNum", monthNum);
        i.putExtra("monthLastDay", monthLastDay);
        i.putExtra("airportCode", this.airportCode);
        i.putExtra("cityName", this.cityName);
        this.startActivity(i);
    }
}

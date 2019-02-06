package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonPressed(View v)
    {
        TextView answerTV = (TextView)this.findViewById(R.id.answerTV);
        answerTV.setText("World");
    }

    public void onNextPageButtonPressed(View v)
    {
        Intent i = new Intent(this, Page2Activity.class);
        this.startActivity(i);
    }
}
